package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

public class Application {

    public static void main(String[] args) {
        String targetNum; // 타겟번호
        String inputNum; // 입력값
        boolean isNext = true;

        while(isNext) {
            // 숫자 생성
            targetNum = makeNumber();
            String targetFirNum = targetNum.substring(0, 1);
            String targetSecNum = targetNum.substring(1, 2);
            String targetThrNum = targetNum.substring(2, 3);
            boolean isCorrect = false;
            while(!isCorrect){
                // 입력값 입력
                System.out.println("숫자를 입력해 주세요 : ");
                inputNum = Console.readLine();
                String inputFirNum = inputNum.substring(0, 1);
                String inputSecNum = inputNum.substring(1, 2);
                String inputThrNum = inputNum.substring(2, 3);
                // 입력값 정수여부, 길이, 중복확인
                isInputInteger(inputNum);
                isInputLength(inputNum,3);
                isInputDuplicate(inputFirNum, inputSecNum, inputThrNum);
                // 결과
                int ballCount = sumBallCount(targetFirNum, targetSecNum, targetThrNum, inputFirNum, inputSecNum, inputThrNum);
                int StrikeCount = sumStrikeCount(targetFirNum, targetSecNum, targetThrNum, inputFirNum, inputSecNum, inputThrNum);
                // 결과출력
                isCorrect = printResult(ballCount, StrikeCount);
            }
            System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요");
            String flagNext = Console.readLine();
            isInputLength(flagNext,1);
            isNext = isNext(flagNext);
        }
    }

    // 숫자생성
    private static String makeNumber() {
        int firNum = Randoms.pickNumberInRange(1, 9);
        int secNum = Randoms.pickNumberInRange(1, 9);
        int thrNum = Randoms.pickNumberInRange(1, 9);
        while (firNum == secNum) {secNum = Randoms.pickNumberInRange(1, 9);}
        while (thrNum == firNum || thrNum == secNum) {thrNum = Randoms.pickNumberInRange(1, 9);}
        return firNum + Integer.toString(secNum) + Integer.toString(thrNum);
    }

    // 입력값 정수 확인
    private static void isInputInteger(String inputNum) {
        if (!inputNum.matches("-?\\d+")) {
          throw new IllegalArgumentException("입력값이 정수가 아닙니다.");
        }
    }

    // 입력값 길이 확인
    private static void isInputLength(String inputNum, int length) {
        if (inputNum.length() != length) {
          throw new IllegalArgumentException("입력값이 " + length + "자리가 아닙니다.");
        }
    }

    // 입력값 중복 확인
    private static void isInputDuplicate(String inputFirNum, String inputSecNum, String inputThrNum) {
        if (inputFirNum.equals(inputSecNum) ||  inputFirNum.equals(inputThrNum) ||  inputSecNum.equals(inputThrNum)) {
          throw new IllegalArgumentException("입력값이 중복되었습니다.");
        }
    }

    // 볼 카운트 확인
    private static int sumBallCount(
            String targetFirNum,
            String targetSecNum,
            String targetThrNum,
            String inputFirNum,
            String inputSecNum,
            String inputThrNum
    ) {
        int ballCount = 0;
        if(targetFirNum.equals(inputSecNum) || targetFirNum.equals(inputThrNum)) {ballCount++;}
        if(targetSecNum.equals(inputFirNum) || targetSecNum.equals(inputThrNum)) {ballCount++;}
        if(targetThrNum.equals(inputFirNum) || targetThrNum.equals(inputSecNum)) {ballCount++;}
        return ballCount;
    }

    // 스트라이크 카운트 확인
    private static int sumStrikeCount(
            String targetFirNum,
            String targetSecNum,
            String targetThrNum,
            String inputFirNum,
            String inputSecNum,
            String inputThrNum
    ) {
        int strikeCount = 0;
        if(targetFirNum.equals(inputFirNum)) {strikeCount++;}
        if(targetSecNum.equals(inputSecNum)) {strikeCount++;}
        if(targetThrNum.equals(inputThrNum)) {strikeCount++;}
        return strikeCount;
    }

    //
    private static boolean printResult(int ballCount, int strikeCount) {
        String resultMassage = ""; // 결과메세지
        boolean isCorrect = false;
        if(ballCount > 0) {resultMassage = ballCount + "볼 ";}
        if(strikeCount > 0) {
            resultMassage += strikeCount + "스트라이크";
            if(strikeCount == 3){
                resultMassage += "\n3개의 숫자를 모두 맞히셨습니다! 게임 종료";
                isCorrect = true;
            }
        }
        if(ballCount == 0 && strikeCount == 0) {resultMassage = "낫싱";}
        System.out.println(resultMassage);
        return isCorrect;
    }

    // 재시작여부 확인
    private static boolean isNext(String flagNext) {
        return "1".equals(flagNext);
    }
}
