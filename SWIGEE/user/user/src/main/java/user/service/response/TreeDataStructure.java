package user.service.response;

import java.util.List;

//@lombok.Data
public class TreeDataStructure {

	private Data data;

    private List<TreeDataStructure> children;

    public static class Data {

        Integer id;

        Integer parentId;

        String name;

        String age;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getParentId() {
            return parentId;
        }

        public void setParentId(Integer parentId) {
            this.parentId = parentId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public List<TreeDataStructure> getChildren() {
		return children;
	}

	public void setChildren(List<TreeDataStructure> children) {
		this.children = children;
	}
}
