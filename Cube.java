import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Hashtable;

public class Cube  {

    int red = 0;
    int green = 1;
    int blue = 2;
    int orange = 3;
    int white = 4;
    int yellow = 5;
    int left = 0;
    int right = 1;

    int[] displayOrder = { yellow, blue, red, green, orange, white }; // Sets the order in which the sides are displayed

    Face redFace;
    Face greenFace;
    Face blueFace;
    Face orangeFace;
    Face whiteFace;
    Face yellowFace;
    Hashtable<Integer, Face> hashCube;
    int score;



    public Cube() {
        redFace = new Face(red);
        greenFace = new Face(green);
        blueFace = new Face(blue);
        orangeFace = new Face(orange);
        whiteFace = new Face(white);
        yellowFace = new Face(yellow);
        hashCube = new Hashtable<Integer, Face>();
        hashCube.put(0, redFace);
        hashCube.put(1, greenFace);
        hashCube.put(2, blueFace);
        hashCube.put(3, orangeFace);
        hashCube.put(4, whiteFace);
        hashCube.put(5, yellowFace);

        whiteFace.leftFace = greenFace;
        whiteFace.rightFace = blueFace;
        whiteFace.topFace = orangeFace;
        whiteFace.bottomFace = redFace;

        redFace.topFace = whiteFace;
        redFace.leftFace = greenFace;
        redFace.rightFace = blueFace;
        redFace.bottomFace = yellowFace;

        yellowFace.topFace = redFace;
        yellowFace.bottomFace = orangeFace;
        yellowFace.leftFace = greenFace;
        yellowFace.rightFace = blueFace;

        orangeFace.leftFace = greenFace;
        orangeFace.rightFace = blueFace;
        orangeFace.bottomFace = whiteFace;
        orangeFace.topFace = yellowFace;

        blueFace.leftFace = whiteFace;
        blueFace.rightFace = yellowFace;
        blueFace.topFace = orangeFace;
        blueFace.bottomFace = redFace;

        greenFace.topFace = orangeFace;
        greenFace.bottomFace = redFace;
        greenFace.leftFace = yellowFace;
        greenFace.rightFace = whiteFace;

    }

    public void setValues(int[][] values) {
        for (int[] faceValue : values) {
            int color = faceValue[4];
            Face iSee = hashCube.get(color);
            iSee.setFace(faceValue);
        }
    }

    public int[][] getValues() {
        int[][] outCube = new int[6][9];
        int row = 0;
        for (int color : displayOrder) {
            Face iSee = hashCube.get(color);
            outCube[row] = iSee.getFace();
            row += 1;
        }
        return outCube;
    }

    public void printCube() {
        int[][] printOut = this.getValues();
        for (int[] row : printOut) {
            String x = "";
            for (int i : row) {
                x += i + " ";
            }
            System.out.println(x);
        }
    }

    public void randomize(int x) {
        // random number generator, with x as seed
    }

    public void rotateClockwise(int color) throws Exception{
        if (color < 0 || color > 5) {
            System.out.println("invalid face");
            return;
        }
        Face iSee = hashCube.get(color);
        iSee.rotate(right);
    }

    public void rotateCounterClockwise(int color) throws Exception{
        if (color < 0 || color > 5) {
            System.out.println("invalid face");
            return;
        }
        Face iSee = hashCube.get(color);
        iSee.rotate(left);
    }


    public static int[][] parse(String filename) throws  Exception{

        Hashtable<String, Integer> colorDict = new Hashtable<String, Integer>();
        colorDict.put("r", 0);
        colorDict.put("g", 1);
        colorDict.put("b", 2);
        colorDict.put("o", 3);
        colorDict.put("w", 4);
        colorDict.put("y", 5);

        BufferedReader br = new BufferedReader(new FileReader(filename));
        int[][] output = new int[6][9];
        for (int i=0; i < 6; i++) {
            String line = br.readLine();
            if (line == null) {
                System.out.println("file is bad");
                System.exit(1);
            }
            String[] in = line.split(",");
            int[] out = new int[9];
            for (int j=0; j < 9; j++) {
                out[j] = colorDict.get(in[i]);
            }
            output[i] = out;
        }
        return output;
    }

    public static void main(String[] args) throws Exception{
        Cube newCube = new Cube();
        newCube.setValues(parse(args[0]));
        newCube.printCube();
        System.out.print('\n');
        newCube.rotateClockwise(0);
        newCube.printCube();
        System.out.print('\n');
        newCube.rotateCounterClockwise(0);
        newCube.printCube();
    }
}
