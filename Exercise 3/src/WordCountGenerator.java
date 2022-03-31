
public class WordCountGenerator {

	public String getWordsLength() {
			
			String text = "Good Morning Everyone";
			String length = String.valueOf(count(text));
			
			return length;
			
		}
	
		public static int count(String text) {
		
			if(text == null || text.isEmpty()) {
				return 0;
			}
			
			String [] word = text.split("\\s+");
			return word.length;
		}
}
