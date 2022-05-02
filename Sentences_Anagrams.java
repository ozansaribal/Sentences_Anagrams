import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


class Result {

    /*
     * Complete the 'countSentences' function below.
     *
     * The function is expected to return a LONG_INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. STRING_ARRAY wordSet
     *  2. STRING_ARRAY sentences
     */

    public static List<Long> countSentences(List<String> wordSet, List<String> sentences) {

        

        long[] count = new long[sentences.size()];
        
        HashMap<String, List<String>> map = new HashMap<>();
        HashMap<String, List<String>> mapToUse = new HashMap<>();
        
        for(String word : wordSet){
            
            char[] charArr = word.toCharArray();
            
            Arrays.sort(charArr);
            
            String sorted = new String(charArr);
            
            if(map.get(sorted) != null){
                
                List<String> list = map.get(sorted);
                
                list.add(word);
                
                map.put(sorted, list);
                
                mapToUse.put(word, list);
                
            }
            else{
                
                List<String> list = new ArrayList<String>();
                
                list.add(word);
                
                map.put(sorted, list);
                
                mapToUse.put(word, list);
                
            }
            
        }
        
        int index = 0;
        
        for(String sentence : sentences){
            
            int c = 1;
            
            String[] strArr = sentence.trim().split(" ");
            
            for(String str : strArr){
                
                if(mapToUse.get(str) != null){
                    
                    List<String> list = mapToUse.get(str);
                    
                    c = c * list.size();
                    
                }
                
            }
            
            count[index++] = c;
            
        }
        
        List<Long> resultList = new ArrayList<Long>();
        
        for(int i = 0; i < count.length; i++){
            
            resultList.add(count[i]);
            
        }
        
        return resultList;

    }

}
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int wordSetCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> wordSet = IntStream.range(0, wordSetCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .collect(toList());

        int sentencesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> sentences = IntStream.range(0, sentencesCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .collect(toList());

        List<Long> result = Result.countSentences(wordSet, sentences);

        bufferedWriter.write(
            result.stream()
                .map(Object::toString)
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
