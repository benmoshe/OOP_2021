public class javaTricks {

    enum LEVEL {
        BEGINNER,
        AMATEUR,
        PROFESSIONAL,
        WORLD_CLASS,
        LEGENDARY
    }

    public static void noEnumExample(){
        int level = 2;

        switch (level) {
            case 1:
                System.out.println("Beginner: *");
                break;
            case 2:
                System.out.println("Amateur: **");
                break;
            case 3:
                System.out.println("Professional: ***");
                break;
            case 4:
                System.out.println("World Class: ****");
                break;
            case 5:
                System.out.println("Legendary *****");
                break;

            default:
                System.out.println("undefined");
        }

    }

    /*
    public static void noEnumExample(){
        int level = 2;

        switch (level) {
            case 1 -> System.out.println("Beginner: *");
            case 2 -> System.out.println("Amateur: **");
            case 3 -> System.out.println("Professional: ***");
            case 4 -> System.out.println("World Class: ****");
            case 5 -> System.out.println("Legendary *****");
            default -> System.out.println("undefined");
        }
    }
     */

    public static void EnumExample(){
        LEVEL level = LEVEL.WORLD_CLASS;
        switch (level) {
            case BEGINNER:
                System.out.println("Beginner: *");
                break;
            case AMATEUR:
                System.out.println("Amateur: **");
                break;
            case PROFESSIONAL:
                System.out.println("Professional: ***");
                break;
            case WORLD_CLASS:
                System.out.println("World Class: ****");
                break;
            case LEGENDARY:
                System.out.println("Legendary *****");
                break;

            default:
                System.out.println("undefined");
        }
    }

    public static void main(String[] args) {
        int num = 1;
        double trick = (num % 2 == 0) ? 0 : 1;
        System.out.println(trick);

        String[] stringArr = {"\nThis","is","for","each",":)\n"};
        for (String s : stringArr) {
            System.out.print(s+" ");
        }
        System.out.println();

        noEnumExample();
        EnumExample();
    }
}
