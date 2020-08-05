/*
 * Copyright (c) 2015 Christian Chevalley
 * This file is part of Project Ethercis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ethercis.graphql.generator;

import com.ethercis.ehr.encode.wrappers.element.ElementWrapper;
import com.ethercis.graphql.generator.tree_node.DataValueNode;
import com.ethercis.graphql.generator.tree_node.NameValueNode;
import com.ethercis.graphql.generator.tree_node.OnClause;
import com.ethercis.jooq.pg.tables.records.TemplateRecord;
import com.ethercis.nary.NaryNode;
import com.ethercis.nary.NaryTree;
import com.ethercis.nary.Walk;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.rm.composition.Composition;
import org.openehr.rm.composition.content.ContentItem;
import org.openehr.rm.composition.content.entry.*;
import org.openehr.rm.composition.content.navigation.Section;
import org.openehr.rm.datastructure.history.Event;
import org.openehr.rm.datastructure.history.History;
import org.openehr.rm.datastructure.itemstructure.*;
import org.openehr.rm.datastructure.itemstructure.representation.Cluster;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datastructure.itemstructure.representation.Item;
import org.openehr.rm.datatypes.basic.DataValue;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.datatypes.uri.DvURI;
import org.openehr.rm.integration.GenericEntry;

import java.util.*;

/**
 * Sequential Event Processor for Composition.<p>
 * used to invalidate composition content to perform updates...
 *
 * @author Christian Chevalley
 * @see ElementWrapper
 */
public class Exemplify implements I_Exemplify {

    NaryTree naryTree = new NaryTree("");

    protected static Logger log = LogManager.getLogger(Exemplify.class);


    protected ExemplifyMode tag_mode = ExemplifyMode.UNDEFINED; //default
    private final boolean allElements; //default
    private Composition generatedComposition = null;

//	private Gson gson = new Gson();

    public static final String INNER_CLASS_LIST = "$INNER_CLASS_LIST$";
    public static final String TAG_ACTION = "action";
    public static final String TAG_ACTION_ARCHETYPE_ID = "action_archetype_id";
    public static final String TAG_ACTIVITIES = "activities";
    public static final String TAG_ACTIVITY = "activity";
    public static final String TAG_ACTIVITY_ID = "action_id";
    public static final String TAG_ARCHETYPE_NODE_ID = "archetype_node_id";
    public static final String TAG_CAREFLOW_STEP = "careflow_step";
    public static final String TAG_COMPOSITION = "composition";
    public static final String TAG_CONTENT = "content";
    public static final String TAG_CONTEXT = "context";
    public static final String TAG_CURRENT_STATE = "current_state";
    public static final String TAG_DATA = "data";
    public static final String TAG_DEFINING_CODE = "defining_code";
    public static final String TAG_DESCRIPTION = "description";
    public static final String TAG_ENTRY = "entry";
    public static final String TAG_EVALUATION = "evaluation";
    public static final String TAG_EVENTS = "events";
    public static final String TAG_GUIDELINE_ID = "guideline_id";
    public static final String TAG_HISTORY = "history";
    public static final String TAG_INSTRUCTION = "instruction";
    public static final String TAG_INSTRUCTION_DETAILS = "instruction_details";
    public static final String TAG_INSTRUCTION_ID = "instruction_id";
    public static final String TAG_ISM_TRANSITION = "ism_transition";
    public static final String TAG_ITEMS = "items";
    public static final String TAG_ITEM = "item";
    public static final String TAG_MATH_FUNCTION = "math_function";
    public static final String TAG_META = "meta";
    public static final String TAG_NARRATIVE = "narrative";
    public static final String TAG_OBSERVATION = "observation";
    public static final String TAG_ORIGIN = "origin";
    public static final String TAG_OTHER_DETAILS = "other_details";
    public static final String TAG_OTHER_CONTEXT = "other_context";
    public static final String TAG_OTHER_PARTICIPATIONS = "other_participations";
    public static final String TAG_PROTOCOL = "protocol";
    public static final String TAG_STATE = "state";
    public static final String TAG_SUMMARY = "summary";
    public static final String TAG_TIME = "time";
    public static final String TAG_TIMING = "timing";
    public static final String TAG_TRANSITION = "transition";
    public static final String TAG_VALUE = "value";
    public static final String TAG_WIDTH = "width";
    public static final String TAG_WORKFLOW_ID = "workflow_id";
    public static final String TAG_CLASS = "$CLASS$";
    public static final String TAG_NAME = "name";
    public static final String TAG_PATH = "$PATH$";
    public static final String TAG_UID = "uid";

    public Exemplify(ExemplifyMode mode) throws IllegalAccessException {
        this.allElements = false;
        this.tag_mode = mode;
//		initTags();
    }

    public Exemplify(boolean allElements) throws IllegalAccessException {
        this.allElements = allElements;
        this.tag_mode = ExemplifyMode.ANY_FIELD;
//		initTags();
    }

    public Exemplify(ExemplifyMode mode, boolean allElements) throws IllegalAccessException {
        this.allElements = allElements;
        this.tag_mode = mode;
//		initTags();
    }


    public Exemplify() throws IllegalAccessException {
        this.allElements = false;
        this.tag_mode = ExemplifyMode.ANY_FIELD;
//		initTags();
    }


    /**
     * main entry method, generate a composition.
     *
     * @param composition
     * @return
     * @throws Exception
     */

    @Override
    public Exemplify generate(Composition composition) throws Exception {
        if (composition == null || composition.getContent() == null || composition.getContent().isEmpty())
            return this;

        //get the annotated fields
        NaryNode<String> attributes = new AttributeNode(composition.getClass(), tag_mode).node();

        NaryNode child = naryTree.addChild(attributes);
        if (child != null) {
            for (ContentItem item : composition.getContent()) {
                NaryNode contentChild = traverse(item, new FieldAlias(TAG_CONTENT, item).alias());
                if (contentChild != null && !contentChild.isEmpty())
                    child.addSibling(contentChild);
            }
        }

        this.generatedComposition = composition;

        return this;
    }


    @Override
    public void processItem(Locatable locatable) throws Exception {

        if (locatable instanceof Item)
            traverse((Item) locatable, TAG_ITEMS);
        else if (locatable instanceof ItemStructure)
            traverse((ItemStructure) locatable, TAG_ITEMS);
        else
            throw new IllegalArgumentException("locatable is not an Item or ItemStructure instance...");

    }


    private void processItem(String tag, Locatable locatable) throws Exception {

        if (locatable instanceof Item)
            traverse((Item) locatable, TAG_ITEMS);
        else if (locatable instanceof ItemStructure)
            traverse((ItemStructure) locatable, TAG_ITEMS);
        else
            throw new IllegalArgumentException("locatable is not an Item or ItemStructure instance...");

    }

    /**
     * main entry method, generate an arbitrary entry (evaluation, observation, instruction, action)
     *
     * @param entry
     * @param entryTag
     * @return
     * @throws Exception
     */
    private void generate(Entry entry, String entryTag) throws Exception {
        traverse(entry, TAG_DATA);
    }

    /**
     * convenience method for processing an Evaluation
     *
     * @param entry
     * @return
     * @throws Exception
     */
    private void generate(Evaluation entry) throws Exception {
        if (entry == null || entry.getData() == null)
            return;

        generate(entry, TAG_EVALUATION);
    }

    /**
     * convenience method for processing an Observation
     *
     * @param entry
     * @return
     * @throws Exception
     */
    private void generate(Observation entry) throws Exception {
        if (entry == null || entry.getData() == null)
            return;

        generate(entry, TAG_OBSERVATION);
    }

    /**
     * convenience method for processing an Instruction
     *
     * @param entry
     * @return
     * @throws Exception
     */
    private void generate(Instruction entry) throws Exception {
        if (entry == null || entry.getActivities() == null)
            return;

        generate(entry, TAG_INSTRUCTION);
    }

    /**
     * convenience method for processing an Instruction
     *
     * @param entry
     * @return
     * @throws Exception
     */
    private void generate(Action entry) throws Exception {
        if (entry == null || entry.getDescription() == null)
            return;

        generate(entry, TAG_ACTION);
    }

    /**
     * convenience method for processing an Activity
     *
     * @param entry
     * @return
     * @throws Exception
     */
    private void generate(Activity entry) throws Exception {
        if (entry == null || entry.getDescription() == null)
            return;

        traverse(entry, TAG_DATA);
    }

//	public void setMode(WalkerOutputMode mode) {
//		this.tag_mode = mode;
//	}


    /**
     * domain level: Observation, evaluation, instruction, action. section, admin etc.
     *
     * @param item
     * @param tag
     * @throws Exception
     */
    private NaryNode traverse(ContentItem item, String tag) throws Exception {

        Map<String, Object> retmap = null;

        if (item == null) {
            return null;
        }

        NaryNode<String> contentNode = new NaryNode<>(tag);

        log.debug("traverse element of class:" + item.getClass() + ", tag:" + tag + ", nodeid:" + item.getArchetypeNodeId());

        if (item instanceof Observation) {
            NaryNode observationNode = contentNode.addChild(new OnClause(item).toString());
            Observation observation = (Observation) item;
            NaryNode child = observationNode.addChild(new AttributeNode(observation.getClass(), tag_mode).node());
            if (child != null) {

                if (observation.getProtocol() != null) {
//                    NaryNode dataNode = new NaryNode(TAG_PROTOCOL);
                    NaryNode protocolNode = traverse(observation.getProtocol(), TAG_PROTOCOL);
                    if (protocolNode != null && !protocolNode.isEmpty()) {
                        child.addSibling(protocolNode);
//                        child.addSibling(dataNode);
                    }
                }

                if (observation.getData() != null) {
//                    NaryNode dataNode = new NaryNode(TAG_DATA);
                    NaryNode historyNode = traverse(observation.getData(), TAG_DATA);
                    if (historyNode != null) {
                        child.addSibling(historyNode);
//                        child.addSibling(dataNode);
                    }
                }

                if (observation.getState() != null)
                    child.addSibling(traverse(observation.getState(), TAG_STATE));
            }

        } else if (item instanceof Evaluation) {
            NaryNode evalNode = contentNode.addChild(new OnClause(item).toString());
            Evaluation evaluation = (Evaluation) item;
            NaryNode child = evalNode.addChild(new AttributeNode(evaluation.getClass(), tag_mode).node());
            if (child != null) {
                if (evaluation.getProtocol() != null) {
                    NaryNode dataNode = new NaryNode(TAG_PROTOCOL);
//                NaryNode itemNode = dataNode.addChild(dataStructureOnClause(evaluation.getData()));
                    if (dataNode != null && !dataNode.isEmpty()) {
                        dataNode.addChild(traverse(evaluation.getProtocol(), TAG_PROTOCOL));
                        child.addSibling(dataNode);
                    }
                }

                if (evaluation.getData() != null) {
                    NaryNode dataNode = new NaryNode(TAG_DATA);
//                NaryNode itemNode = dataNode.addChild(dataStructureOnClause(evaluation.getData()));
                    dataNode.addChild(traverse(evaluation.getData(), TAG_DATA));
                    child.addSibling(dataNode);
                }
            }

        } else if (item instanceof Instruction) {
            NaryNode instructionNode = contentNode.addChild(new OnClause(item).toString());
            Instruction instruction = (Instruction) item;
            NaryNode child = instructionNode.addChild(new AttributeNode(instruction.getClass(), tag_mode).node());

            if (instruction.getProtocol() != null) {
                NaryNode dataNode = new NaryNode(TAG_PROTOCOL);
                if (dataNode != null && !dataNode.isEmpty()) {
                    dataNode.addChild(traverse(instruction.getProtocol(), TAG_PROTOCOL));
                    child.addSibling(dataNode);
                }
            }

            if (instruction.getActivities() != null) {
                for (Activity activity : instruction.getActivities()) {
                    child.addSibling(traverse(activity, TAG_ACTIVITIES));
                }
            }

        } else if (item instanceof Action) {
            NaryNode actionNode = contentNode.addChild(new OnClause(item).toString());
            Action action = (Action) item;
            NaryNode child = actionNode.addChild(new AttributeNode(action.getClass(), tag_mode).node());

            if (action.getProtocol() != null) {
                NaryNode dataNode = new NaryNode(TAG_PROTOCOL);
                NaryNode protocolChild = traverse(action.getProtocol(), TAG_PROTOCOL);
                if (protocolChild != null && !protocolChild.isEmpty()) {
                    dataNode.addChild(traverse(action.getProtocol(), TAG_PROTOCOL));
                    child.addSibling(dataNode);
                }
            }

            if (action.getDescription() != null) {
                NaryNode dataNode = new NaryNode(TAG_DESCRIPTION);
//                NaryNode itemNode = dataNode.addChild(dataStructureOnClause(evaluation.getData()));
                dataNode.addChild(traverse(action.getDescription(), TAG_DESCRIPTION));
                child.addSibling(dataNode);
            }

        } else if (item instanceof Section) {
            NaryNode sectionNode = contentNode.addChild(new OnClause(item).toString());
            Section section = (Section) item;
            NaryNode child = sectionNode.addChild(new AttributeNode(section.getClass(), tag_mode).node());
            if (child != null) {
                for (ContentItem contentItem : section.getItems()) {
                    NaryNode itemNode = traverse(contentItem, TAG_ITEMS);
                    if (!itemNode.isEmpty()) {
                        String qualification = new FieldAlias(TAG_ITEMS, contentItem).alias();
                        itemNode.setData(qualification);
                        child.addSibling(itemNode);
                    }
                }
            }

        } else if (item instanceof AdminEntry) {
            AdminEntry adminEntry = (AdminEntry) item;
            if (adminEntry.getData() != null)
                traverse(adminEntry.getData(), TAG_DATA);

        } else if (item instanceof GenericEntry) {
            GenericEntry genericEntry = (GenericEntry) item;

            traverse(genericEntry.getData(), TAG_DATA);

        } else {
            log.warn("This item is not handled!" + item.nodeName());
        }

        return contentNode;
    }


    /**
     * History level in composition
     *
     * @param tag
     * @throws Exception
     */
    private NaryNode traverse(History history, String tag) throws Exception {
        if (history == null) {
            return null;
        }

        log.debug("traverse history:" + history);

        //CHC: 160531 add explicit name
        NaryNode historyNode = new NaryNode(tag);

        if (history.getSummary() != null) {
            historyNode.addChild(traverse(history.getSummary(), TAG_SUMMARY));
        }

        if (history.getEvents() != null) {
//            NaryNode eventsNode = new NaryNode(TAG_EVENTS);
//            historyNode.addChild(eventsNode);
            for (Object event1 : history.getEvents()) {
                Event event = (Event) event1;
                historyNode.addChild(traverse(event, TAG_EVENTS));

            }
        }
        return historyNode;
    }

    private NaryNode traverse(Activity activity, String tag) throws Exception {
        if (activity == null) {
            return null;
        }

        log.debug("traverse activity:" + activity);

        //CHC: 160531 add explicit name
        NaryNode activityNode = new NaryNode(tag);

        if (activity.getDescription() != null) {
            NaryNode descriptionNode = new NaryNode(TAG_DESCRIPTION);
            descriptionNode.addChild(traverse(activity.getDescription(), TAG_DESCRIPTION));
            activityNode.addChild(descriptionNode);

        }

        return activityNode;
    }


    private NaryNode traverse(Event event, String tag) throws Exception {
        if (event == null) {
            return null;
        }

        log.debug("traverse event:" + event);
        NaryNode eventNode = new NaryNode(tag);
        NaryNode child = eventNode.addChild(new AttributeNode(event.getClass(), tag_mode).node());

        if (child != null) {
            if (event.getData() != null) {
                NaryNode dataNode = new NaryNode(TAG_DATA);
                NaryNode eventDataNode = traverse(event.getData(), TAG_DATA);
                if (eventDataNode != null) {
                    dataNode.addChild(eventDataNode);
                    child.addSibling(dataNode);
                }
            }
            if (event.getState() != null) {
                NaryNode stateNode = new NaryNode(TAG_STATE);
                NaryNode eventStateNode = traverse(event.getState(), TAG_STATE);
                if (eventStateNode != null) {
                    stateNode.addChild(eventStateNode);
                    child.addSibling(stateNode);
                }
            }
        }

        return eventNode;
    }

    /**
     * ItemStructure: single, tree or table
     *
     * @param item
     * @param uppertag
     * @throws Exception
     */
    private NaryNode traverse(ItemStructure item, String uppertag) throws Exception {

        log.debug("traverse itemstructure:" + item);

        if (item == null) {
            return null;
        }

        NaryNode<String> itemStructureNode = null;
        if (item instanceof ItemSingle) {
            itemStructureNode = new NaryNode<>(TAG_ITEM);
            ItemSingle itemSingle = (ItemSingle) item;
            if (itemSingle.getItem() != null) {
                itemStructureNode.addChild(traverse(itemSingle.getItem(), TAG_ITEM));
            }
        } else if (item instanceof ItemList) {
            itemStructureNode = new NaryNode<>(new FieldAlias(TAG_ITEMS, item).alias());
            ItemList list = (ItemList) item;
            if (list.getItems() != null) {
//                itemStructureNode = new NaryNode<>("... on ITEM_LIST");
                for (Element element : list.getItems()) {
                    itemStructureNode.addChild(traverse(element, TAG_ITEMS));
                }
            }
        } else if (item instanceof ItemTree) {
//            itemStructureNode = new NaryNode<>(alias(TAG_ITEMS, item));
            ItemTree tree = (ItemTree) item;

            if (tree.getItems() != null && tree.getItems().size() > 0) {
                itemStructureNode = new NaryNode<>("... on ITEM_TREE");
                NaryNode treeChild = null;
                for (Item subItem : tree.getItems()) {
                    if (treeChild == null)
                        treeChild = itemStructureNode.addChild(traverse(subItem, TAG_ITEMS));
                    else
                        treeChild.addSibling(traverse(subItem, TAG_ITEMS));
                }
            }

        } else if (item instanceof ItemTable) {
            itemStructureNode = new NaryNode<>(new FieldAlias(TAG_ITEMS, item).alias());
            ItemTable table = (ItemTable) item;
            if (table.getRows() != null) {
//                itemStructureNode = new NaryNode<>("... on ITEM_TABLE");
                for (Item subItem : table.getRows()) {
                    itemStructureNode.addChild(traverse(subItem, TAG_ITEMS));
                }
            }
        }
        return itemStructureNode;
    }

    /**
     * Element level, normally cannot go deeper...
     *
     * @param item
     * @param tag
     * @throws Exception
     */
    private NaryNode traverse(Item item, String tag) throws Exception {
        log.debug("traverse item:" + item);

        if (item == null) {
            return null;
        }

        NaryNode<String> itemNode = null;

        if (item instanceof Element) {
            itemNode = new NaryNode<>(new FieldAlias(tag, item).alias());
            NaryNode elementNode = itemNode.addChild(new DataValueNode(tag_mode, (Element) item).node());
//            elementNode.addChild(attributes(((Element)item).getValue().getClass()));
        } else if (item instanceof ElementWrapper) {
            itemNode = new NaryNode<>(new FieldAlias(tag, item).alias());
            NaryNode elementWrapperNode = itemNode.addChild(new OnClause(Element.class).toString());

            NaryNode child = elementWrapperNode.addChild(new AttributeNode(Element.class, tag_mode).node());
            NaryNode nameNode = child.addSibling(TAG_NAME);
            nameNode.addChild(new NameValueNode(tag_mode, ((ElementWrapper) item).getName().getClass()).node());

            NaryNode valueNode = child.addSibling(TAG_VALUE);
            valueNode.addChild(new DataValueNode(tag_mode, (ElementWrapper) item).node());


//            elementNode.addChild(attributes(((ElementWrapper)item).getAdaptedElement().getValue().getClass()));
        } else if (item instanceof Cluster) {

            Cluster cluster = (Cluster) item;
            itemNode = new NaryNode<>(new FieldAlias(tag, cluster).alias());
            NaryNode clusterWrapper = itemNode.addChild(new OnClause(Cluster.class).toString());
            if (cluster.getItems() != null) {
                NaryNode child = null;
                String clusterItemNodeId = null;
                for (Item clusterItem : cluster.getItems()) {
                    if (clusterItemNodeId != null){
                        if (clusterItemNodeId.equals(clusterItem.getArchetypeNodeId()) && tag_mode.equals(ExemplifyMode.REQUIRED_FIELDS_ONLY)) {
                            continue;
                        }
                    }
                    clusterItemNodeId = clusterItem.getArchetypeNodeId();
                    if (child == null)
                        child = clusterWrapper.addChild(traverse(clusterItem, TAG_ITEMS));
                    else
                        child.addSibling(traverse(clusterItem, TAG_ITEMS));
                }
            }
//            itemNode.addChild(clusterNode);
        }
        return itemNode;
    }

    @Override
    public String modelize() {
        Walk walk = new Walk(new NaryTree(naryTree.child())).prettyPrint();
        return walk.inOrder();
    }

    @Override
    public String modelize(Integer tabs) {
        Walk walk = new Walk(new NaryTree(naryTree.child())).prettyPrint();
        return walk.inOrder();
    }

//    public String modelize(String prefix) {
//        return prefix + modelize();
//    }

    @Override
    public String modelize(String prefix, String suffix) {
        //display composition meta data in header
        StringBuffer stringBuffer = new StringBuffer();
        if (generatedComposition != null)
            stringBuffer.append("# templateId:" + generatedComposition.getArchetypeDetails().getTemplateId() + "\n");
        stringBuffer.append("# created on:" + DateTime.now().toString() + "\n");
        if (!tag_mode.equals(ExemplifyMode.UNDEFINED))
            stringBuffer.append("# Creation mode:" + tag_mode + "\n");
        stringBuffer.append("#\n");
        return stringBuffer + prefix + modelize()+suffix;
    }

    @Override
    public String modelize(String prefix, String suffix, Integer tabs) {
        //display composition meta data in header
        StringBuffer stringBuffer = new StringBuffer();
        if (generatedComposition != null)
            stringBuffer.append("# templateId:" + generatedComposition.getArchetypeDetails().getTemplateId() + "\n");
        stringBuffer.append("# created on:" + DateTime.now().toString() + "\n");
        if (!tag_mode.equals(ExemplifyMode.UNDEFINED))
            stringBuffer.append("# Creation mode:" + tag_mode + "\n");
        stringBuffer.append("#\n");
        return stringBuffer + prefix + modelize(tabs)+suffix;
    }
}
