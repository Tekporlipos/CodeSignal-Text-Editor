import java.util.*;

public class Main {
    public static void main(String[] args) {
        String[] ope = {"TYPE Great Britain is the capital of London","SELECT 0 12","COPY","SELECT 32 37","COPY","PASTE 2","SELECT 0 12","PASTE","MOVE_CURSOR 32","TYPE !"};
        System.out.println(solution(ope));//Test array
        //
    }


   public static String  solution(String[] operations) {
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<String> copy = new ArrayList<>();

        int[] selected = {0,0};
        Integer cursor = 0; 

        for(String operation : operations){

            String operationMethod = operation.split(" ")[0];
            System.out.println(operationMethod);
            if(operationMethod.contains("TYPE")){

                String type = operation.trim().substring(5);
                stringBuilder.insert(cursor, type);
                cursor = stringBuilder.length();
                selected[0] = 0;
                selected[1] = 0;
            }
            else if(operationMethod.contains("SELECT")){

                String select = operation.substring(6);
                String[] temp = select.trim().split(" ");
                selected[0] = Integer.parseInt(temp[0].trim());
                selected[1] = Integer.parseInt(temp[1].trim());
                cursor = selected[1];
            }

            else if(operationMethod.contains("MOVE_CURSOR")){
                String move = operation.trim().substring(11);
                  cursor += Integer.parseInt(move.trim())+1;
            }
             else if(operationMethod.contains("COPY")){
                 cursor = 0;
                 if(selected[1] != 0){
                  copy.add(stringBuilder.substring(selected[0],selected[1]));
                 }
             }

            else if(operationMethod.contains("PASTE")){
                String clean = operation.trim().substring(5);
                int past = copy.size()-1;
                if(!clean.trim().isEmpty()){
                    past = Integer.parseInt(clean.trim());
                }

                if(!copy.isEmpty()){
             stringBuilder.replace(selected[0], selected[1], copy.get(copy.size()-past));
                      }
                cursor = selected[0]+copy.get(copy.size()-past).length();
                 }
            System.out.println(selected[0]);
            System.out.println(selected[1]);
            System.out.println(copy);
            System.out.println(cursor+"cursor");
        }



        return stringBuilder.toString();
    }

}
