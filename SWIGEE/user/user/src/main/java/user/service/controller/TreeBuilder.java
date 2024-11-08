package user.service.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import user.service.response.TreeDataStructure;

@RestController
public class TreeBuilder {

	@GetMapping("/get/tree/structure/data")
	public ResponseEntity<List<TreeDataStructure>> main() {
		List<TreeDataStructure> treeDataStructure = null;
		List<Object[]> parentChildList = parentChildList();
		List<Map<String, Object>> listOfRecords = listOfRecords();

		treeDataStructure = buildTreeData(parentChildList, listOfRecords);

		return new ResponseEntity<>(treeDataStructure, HttpStatus.OK);
	}
	
	// =========================== Parent Child Details ==============================

	private static List<Object[]> parentChildList() {
		List<Object[]> dataList = new ArrayList<>();

		// Add some sample data with id, parent_id, and name
		dataList.add(new Object[] { 1, null, "Root" }); // Root node (no parent)
		dataList.add(new Object[] { 2, 1, "Child 1 of Root" }); // Child of Root
		dataList.add(new Object[] { 3, 1, "Child 2 of Root" }); // Another child of Root
		dataList.add(new Object[] { 4, 2, "Child 1 of Child 1" }); // Nested child under Child 1
		dataList.add(new Object[] { 5, 3, "Child 1 of Child 2" }); // Nested child under Child 2

		return dataList;
	}

	// ============================ Record List =================================

	private static List<Map<String, Object>> listOfRecords() {
		List<Map<String, Object>> list = new ArrayList<>();
		list.add(Map.of("id", 4, "parentId", 2, "name", "ghadei", "age", "26"));
		list.add(Map.of("id", 5, "parentId", 2, "name", "samal", "age", "25"));
		list.add(Map.of("id", 6, "parentId", 3, "name", "ranjan", "age", "25"));
		return list;
	}

	// ============================= Tree Data ==============================

	private static List<TreeDataStructure> buildTreeData(List<Object[]> parentChildList,
			List<Map<String, Object>> listOfRecords) {
		List<TreeDataStructure> treeList = new ArrayList<>();
		Map<Integer, TreeDataStructure> nodeMap = new HashMap<>();

		// Step 1: Check if parentChildList is not null and not empty
		if (parentChildList != null && !parentChildList.isEmpty()) {
			// Step 2: Create a map to hold nodes by their ID

			// Step 3: Create nodes from parentChildList
			for (Object[] record : parentChildList) {
				int id = (int) record[0];
				Integer parentId = (Integer) record[1];
				String name = (String) record[2];
//				String age = (String) record[3];

				// Create a new TreeDataStructure node
				TreeDataStructure node = new TreeDataStructure();
				TreeDataStructure.Data nodeData = new TreeDataStructure.Data();
				nodeData.setId(id);
				nodeData.setParentId(parentId);
				nodeData.setName(name);
//				nodeData.setAge(age);
				node.setData(nodeData); // Set the Data object
				node.setChildren(new ArrayList<>()); // Initialize children list

				// Add node to the map
				nodeMap.put(id, node);

				// Step 4: If there's no parentId, it's a root node
				if (parentId == null) {
					treeList.add(node);
				} else {
					// Add to parent's children if parentId exists
					if (nodeMap.containsKey(parentId)) {
						nodeMap.get(parentId).getChildren().add(node);
					}
				}
			}
		}

		// Step 5: Check if listOfRecords is not null and not empty
		if (listOfRecords != null && !listOfRecords.isEmpty()) {
			// Step 6: Add leaf nodes from listOfRecords
			for (Map<String, Object> record : listOfRecords) {
				int id = (int) record.get("id");
				Integer parentId = (Integer) record.get("parentId");
				String name = (String) record.get("name");
				String age = (String) record.get("age");

				// Create a new leaf node
				TreeDataStructure leafNode = new TreeDataStructure();
				TreeDataStructure.Data leafNodeData = new TreeDataStructure.Data();
				leafNodeData.setId(id);
				leafNodeData.setParentId(parentId);
				leafNodeData.setName(name);
				leafNodeData.setAge(age);
				leafNode.setData(leafNodeData); // Set the Data object

				// Step 7: Add leaf node to the corresponding parent
				if (parentId != null && nodeMap.containsKey(parentId)) {
					nodeMap.get(parentId).getChildren().add(leafNode);
				}
			}
		}

		return treeList;
	}
}
