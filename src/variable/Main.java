class ReferenceType {
    public int number = 5;
}

public class Main {

    public static void main(String[] args) {
        /** 변수 선언 및 초기화 */
        // Primitive Type
        int number = 5; // 오른쪽 5를 '리터럴' 이라고 한다.
        // Reference Type
        ReferenceType referenceType = new ReferenceType();

        /** 기본형과 참조형의 차이 확인 */
        // Primitive Type
        System.out.println("Primitive Type: " + number);
        // Reference Type
        System.out.println("Reference Type: " + referenceType);
    }
}
