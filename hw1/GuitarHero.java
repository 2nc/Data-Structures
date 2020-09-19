/**
 * @author wnc
 */
import es.datastructur.synthesizer.GuitarString;


public class GuitarHero {
    private static final double CONCERT_A = 440.0;
    private static final double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);
    private static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    static int num = 0;
    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        GuitarString stringA = new GuitarString(CONCERT_A);
        GuitarString stringC = new GuitarString(CONCERT_C);
        GuitarString[] stringXX = new GuitarString[37];
        for(int i = 0; i < 37; i++){
            stringXX[i] = new GuitarString(CONCERT_A * Math.pow(2, (i-24) / 12.0));
        }
        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if(keyboard.indexOf(key) != -1){
                    num = keyboard.indexOf(key);
                    stringXX[num].pluck();
                }
            }

            /* compute the superposition of samples */
            //double sample = stringA.sample() + stringC.sample();
            double sample = 0;
            for(int i = 0; i < 37 ; i++){
                sample += stringXX[i].sample();
            }
            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            //stringA.tic();
            //stringC.tic();
            for(int i = 0; i < 37 ; i++){
                stringXX[i].tic();
            }

        }
    }
}

