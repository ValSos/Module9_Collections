public class MyCollectionsTest {
    public static void main(String[] args) {


        MyHashMap<String,Integer> studentsMarks = new MyHashMap<>();
        studentsMarks.put("Ivanenko",100);
        studentsMarks.put("Petrenko",90);
        studentsMarks.put("Shevchenko",80);
        studentsMarks.put("Kovalenko",95);
        studentsMarks.put("Popova",100);
        studentsMarks.put("Mazur",74);
        studentsMarks.put("Ivanova",98);
        studentsMarks.put("Kopot",63);
        studentsMarks.put("Salaev",87);
        System.out.println(studentsMarks.size());
        studentsMarks.put("Frgrzko",100);
        studentsMarks.put("esrha",100);
        studentsMarks.put(";a,f",100);
        studentsMarks.put("lol",100);
        studentsMarks.put("kek",100);
        studentsMarks.put("4eburek",100);

        System.out.println(studentsMarks);
        System.out.println(studentsMarks.size());
    }

}
