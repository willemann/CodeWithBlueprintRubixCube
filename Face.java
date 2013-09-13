import java.io.BufferedReader;
import java.io.FileReader;


public class Face extends ColorValues{
    int faceValue;
    int[][] tiles = new int[3][3];

    Face leftFace;
    Face rightFace;
    Face topFace;
    Face bottomFace;

    private void validityCheck() throws Exception{
//        if (leftFace == null || rightFace == null || topFace == null || bottomFace == null){
//            throw new Exception(faceValue + "face not valid");
//        }
    }

    public Face(int faceValue){
        this.faceValue = faceValue;
        this.leftFace = null;
        this.rightFace = null;
        this.topFace = null;
        this.bottomFace = null;
    }

    public void setFace(int[] entireFace){
        tiles[0][0] = entireFace[0];
        tiles[0][1] = entireFace[1];
        tiles[0][2] = entireFace[2];
        tiles[1][0] = entireFace[3];
        tiles[1][1] = entireFace[4];
        tiles[1][2] = entireFace[5];
        tiles[2][0] = entireFace[6];
        tiles[2][1] = entireFace[7];
        tiles[2][2] = entireFace[8];
    }

    public int[] getFace(){
        int[] entireFace = new int[9];
        entireFace[0] = tiles[0][0];
        entireFace[1] = tiles[0][1];
        entireFace[2] = tiles[0][2];
        entireFace[3] = tiles[1][0];
        entireFace[4] = tiles[1][1];
        entireFace[5] = tiles[1][2];
        entireFace[6] = tiles[2][0];
        entireFace[7] = tiles[2][1];
        entireFace[8] = tiles[2][2];
        return entireFace;
    }

    //
    public Face(int faceValue, Face left, Face right, Face top, Face bottom, int[] colorValues){
        this.faceValue = faceValue;

        setFace(colorValues);

        this.leftFace = left;
        this.rightFace = right;
        this.topFace = top;
        this.bottomFace = bottom;
    }



//    getters and setters
    int[] getTopRowVals() throws Exception{
        validityCheck();
        return tiles[0];
    }

    int[] getMidRowVals() throws Exception{
        validityCheck();
        return tiles[1];
    }

    int[] getBotRowVals() throws Exception{
        validityCheck();
        return tiles[2];
    }

    void setTopRowVals(int[] values) throws Exception{
        validityCheck();
        tiles[0] = values;

    }

    void setMidRowVals(int[] values) throws Exception{
        validityCheck();
        tiles[1] = values;

    }
    void setBotRowVals(int[] values) throws Exception{
        validityCheck();
        tiles[2] = values;
    }

    //    getters and setters
    int[] getLeftColVals() throws Exception{
        validityCheck();
        int[] toReturn = {tiles[0][0], tiles[1][0], tiles[2][0]};
        return toReturn;
    }

    int[] getMidColVals() throws Exception{
        validityCheck();
        int[] toReturn = {tiles[0][1], tiles[1][1], tiles[2][1]};
        return toReturn;
    }

    int[] getRightColVals() throws Exception{
        validityCheck();
        int[] toReturn = {tiles[0][2], tiles[1][2], tiles[2][2]};
        return toReturn;
    }

    void setLeftColVals(int[] values) throws Exception{
        validityCheck();
        tiles[0][0] = values[0];
        tiles[1][0] = values[1];
        tiles[2][0] = values[2];
    }

    void setMidColVals(int[] values) throws Exception{
        validityCheck();
        tiles[0][1] = values[0];
        tiles[1][1] = values[1];
        tiles[2][1] = values[2];

    }
    void setRightColVals(int[] values) throws Exception{
        validityCheck();
        tiles[0][2] = values[0];
        tiles[1][2] = values[1];
        tiles[2][2] = values[2];
    }


    public void rotate(int direction) throws Exception {
        final int RIGHT = 1;
        final int LEFT = 0;

        int[] topRow = getTopRowVals();
        int[] midRow = getMidRowVals();
        int[] botRow = getBotRowVals();
        int[] newTop = new int[3];
        int[] newMid = new int[3];
        int[] newBot = new int[3];

        // since there are only 9 values, it's easier just to hardcode it...
        if (direction == RIGHT) {
            newTop[0] = botRow[0];
            newTop[1] = midRow[0];
            newTop[2] = topRow[0];
            newMid[0] = botRow[1];
            newMid[1] = midRow[1];
            newMid[2] = topRow[1];
            newBot[0] = botRow[2];
            newBot[1] = midRow[2];
            newBot[2] = topRow[2];


            int[] temp = leftFace.getRightColVals();
            leftFace.setRightColVals(bottomFace.getTopRowVals());
            bottomFace.setTopRowVals(rightFace.getLeftColVals());
            rightFace.setLeftColVals(topFace.getBotRowVals());
            topFace.setBotRowVals(temp);
        }
        if (direction == LEFT) {
            newTop[0] = topRow[2];
            newTop[1] = midRow[2];
            newTop[2] = botRow[2];
            newMid[0] = topRow[1];
            newMid[1] = midRow[1];
            newMid[2] = botRow[1];
            newBot[0] = topRow[0];
            newBot[1] = midRow[0];
            newBot[2] = botRow[0];

            int[] temp = leftFace.getRightColVals();
            leftFace.setRightColVals(topFace.getBotRowVals());
            topFace.setBotRowVals(rightFace.getLeftColVals());
            rightFace.setLeftColVals(bottomFace.getTopRowVals());
            bottomFace.setTopRowVals(temp);
        }
    }
}
