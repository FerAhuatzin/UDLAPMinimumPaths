import java.awt.*;
import java.beans.BeanDescriptor;

public class Main {

    public static void main(String[] args) {
        String EP = "Ring-road entrance";
        String EJ = "Meditation garden entrance";
        String EG = "Gaos college entrance";
        String ER = "Main entrance";
        String EC = "Courts entrance";
        String CC = "Cain Murray college";
        String CG = "Jose Gaos College";
        String CB = "Ignacio Bernal college";
        String CL = "Ray Lindley college";
        String CE = "Student center";
        String BI = "Library";
        String FN = "Fountain";
        String HA = "Estate";
        String AG = "Agora";
        String AU = "Auditorium";
        String HU = "Humanities building";
        String IA = "Engeeniering and sciences building";
        String CS = "Buiseness and socials building";
        String SL = "Health building";

        Graph udlap = new Graph(19);
        udlap.addNodeName(EP); //node 0
        udlap.addNodeName(EJ); //node 1
        udlap.addNodeName(EG); //node 2
        udlap.addNodeName(ER); //node 3
        udlap.addNodeName(EC); //node 4
        udlap.addNodeName(CC); //node 5
        udlap.addNodeName(CG); //node 6
        udlap.addNodeName(CB); //node 7
        udlap.addNodeName(CL); //node 8
        udlap.addNodeName(CE); //node 9
        udlap.addNodeName(BI); //node 10
        udlap.addNodeName(FN); //node 11
        udlap.addNodeName(HA); //node 12
        udlap.addNodeName(AG); //node 13
        udlap.addNodeName(AU); //node 14
        udlap.addNodeName(HU); //node 15
        udlap.addNodeName(IA); //node 16
        udlap.addNodeName(CS); //node 17
        udlap.addNodeName(SL); //node 18

        udlap.addEdgeByName(EP, IA,492.18);
        udlap.addEdgeByName(EP, CB,196.28);
        udlap.addEdgeByName(EP, CG,646.8);

        udlap.addEdgeByName(CB, HU,156.78);
        udlap.addEdgeByName(CB, HA,251.29);
        udlap.addEdgeByName(CB, EJ,298.52);

        udlap.addEdgeByName(HU, IA,173.5);
        udlap.addEdgeByName(HU, AG,52.5);
        udlap.addEdgeByName(HU, HA,180.18);
        udlap.addEdgeByName(HU, EJ,263.65);

        udlap.addEdgeByName(EJ, HA,262.92);
        udlap.addEdgeByName(EJ, CS,284.69);
        udlap.addEdgeByName(EJ, EG,269.63);
        udlap.addEdgeByName(EJ, CG,308.38);

        udlap.addEdgeByName(AG, IA,173.5);
        udlap.addEdgeByName(AG, BI,256.21);
        udlap.addEdgeByName(AG, FN,223.84);
        udlap.addEdgeByName(AG, AU,193.48);
        udlap.addEdgeByName(AG, HA,178.25);

        udlap.addEdgeByName(HA, IA,175.5);
        udlap.addEdgeByName(HA, AU,108.35);
        udlap.addEdgeByName(HA, FN,128.54);
        udlap.addEdgeByName(HA, CS,184.97);

        udlap.addEdgeByName(IA, ER,466.35);
        udlap.addEdgeByName(IA, BI,188.9);
        udlap.addEdgeByName(IA, FN,183.73);
        udlap.addEdgeByName(IA, AU,145.56);

        udlap.addEdgeByName(AU, FN,42.05);
        udlap.addEdgeByName(AU, BI,98.23);

        udlap.addEdgeByName(CS, FN,81.11);
        udlap.addEdgeByName(CS, CE,114.37);
        udlap.addEdgeByName(CS, CL,194.54);
        udlap.addEdgeByName(CS, EG,235.8);

        udlap.addEdgeByName(FN, BI,64.21);
        udlap.addEdgeByName(FN, CE,87.48);
        udlap.addEdgeByName(FN, CL,161.39);

        udlap.addEdgeByName(BI, CC,92.47);
        udlap.addEdgeByName(BI, CE,110.69);

        udlap.addEdgeByName(CG, EG,24.13);
        udlap.addEdgeByName(CG, EC,271.13);

        udlap.addEdgeByName(EG, CE,256.56);
        udlap.addEdgeByName(EG, CL,248.48);
        udlap.addEdgeByName(EG, EP,249.65);

        udlap.addEdgeByName(CE, CC,118.44);
        udlap.addEdgeByName(CE, CL,78.99);

        udlap.addEdgeByName(CC, ER,321.63);

        udlap.addEdgeByName(ER, SL,405.17);

        udlap.addEdgeByName(CL, SL,95.53);

        udlap.addEdgeByName(SL, EC,236.71);

        udlap.askMinimumPaths();

    }
}
