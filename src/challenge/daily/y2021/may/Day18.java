package challenge.daily.y2021.may;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Day18 {

	public static class File implements Comparable{
		private String path;
		private String name;
		private String content;
		
		public File(String path, String name, String content) {
			this.path=path;
			this.name=name;
			this.content=content;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((content == null) ? 0 : content.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			File other = (File) obj;
			if (content == null) {
				if (other.content != null)
					return false;
			} else if (!content.equals(other.content))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "File [path=" + path + ", name=" + name + ", content=" + content + "]"; //return path+"/"+name;
		}

		@Override
		public int compareTo(Object arg0) {
			File other = (File) arg0;
			
			return this.getContent().compareTo(other.getContent());
		}


		
		
		
	}
	
    public static List<List<String>> findDuplicate(String[] paths) {
        List<File> files = new ArrayList<File>();
        File temp = null;
        
    	for (String string : paths) {
    		files.addAll(createFile(string));
		}
    	
    	//NOW WE HAVE ALL THE FILES FILTERED CORRECTLY. COMPARE WITH EACH OTHER TO SOLVE THE PROBLEM
    	Map<String, Long> cesar = files.stream().collect(Collectors.groupingBy(File::getContent,
    			TreeMap::new,
    			Collectors.counting()));
    	System.out.println(cesar);
    	
    	files.stream().sorted().forEach(System.out::println);
    	
    	
    	
    	
    	return null;
    }
    
	private static List<File> createFile(String string) {
		List<File> files = new ArrayList<File>();
		//1st split ' '
		String[] first = string.split(" ");
		//2nd split '('
		//
		File temp = null;
		for (int i = 1; i < first.length; i++) {
			String[] splitted = first[i].split("\\(");
			
			
			
			files.add(new File(first[0], splitted[0], splitted[1].substring(0, splitted[1].length()-1 ) ));
		}
		
		//new File(first[0], second[0],second[1].substring(0, second[1].length()-1 ) );
		
		return files;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] paths = {"root/a 1.txt(abcd) 2.txt(efgh)","root/c 3.txt(abcd)","root/c/d 4.txt(efgh)","root 4.txt(efgh)"};
		System.out.println(findDuplicate(paths));
	}
	
	
	
}
