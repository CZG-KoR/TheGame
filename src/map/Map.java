package map;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Map {
    /*
     * 2D-Arraylist
     * beginnt bei x = 0 und y = 0, links oben
     * geht bis x = width-1, und bis y = height-1
     * Jede Arraylist<Feld> steht für eine Spalte (gleiche x-Koordinate)
     */

    private ArrayList<ArrayList<Feld>> felder;

    // Dimensionen des Feldes
    private int height;
    private int width;

    public Map(int heigth, int width) {
        this.height = heigth;
        this.width = width;

        generateMap();
    }

    // GET-Function
    public Feld getFeld(int xcoord, int ycoord) {
        return felder.get(xcoord).get(ycoord);
    }

    public String getTerrainName(int xcoord, int ycoord) {
        return getFeld(xcoord, ycoord).getTerrainName();
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public void generateMap() {
        Random r = new Random();
        
        this.felder = new ArrayList<>();
        Terrain t = new Terrain("grass");

        // Generieren einer neuen mit Grass gefüllten Map / Grundlage der Map Erstellung
        for (int i = 0; i < this.height; i++) {
            ArrayList<Feld> zeile = new ArrayList<>();

            // x-Koordinate
            for (int j = 0; j < this.width; j++) {
                Feld f = new Feld(t, 0, i, j);
                zeile.add(f);
            }

            // fügt neue zeile hinzu
            this.felder.add(zeile);
        }

        Noise n = new Noise(r, 10, width, height);
        n.initialise();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (n.getNoiseAt(x, y) < (float) 0.8)
                    this.setT(x, y, "desert");
                if (n.getNoiseAt(x, y) < (float) 0.48)
                    this.setT(x, y, "water");
                if (n.getNoiseAt(x, y) > (float) 4)
                    this.setT(x, y, "forest");
                if (n.getNoiseAt(x, y) > (float) 5.5)
                    this.setT(x, y, "light_mountain");
            }
        }

        // water-pruning
        /*
         * löscht Wasserfelder, wenn limit Anzahl an anderen darum ist
         * Sodass kleine "Pfützen" wegfallen
         */
        
        String[] LandTerrains = {"grass", "forest", "desert", "light_mountains"};
        String[] Ocean = {"water"};
        String[] LowerTerrains = {"grass", "forest", "desert", "water"};
        String[] PeakMountains = {"peak_mountain"};
        
        Pruning("water", 1, LandTerrains, "desert");
        
        //Insel-Protokoll
        /*
            falls so viel wasser da ist >=1000 -> erstellung einer Insel & vergrößerung von Landmassen
        */
        
        while(this.getAmountofTerrain("water") >= 1000) IslandProtocoll();
        
        
        Pruning("forest", 2, Ocean, "grass");
        Pruning("light_mountain", 1, LowerTerrains, "forest");
        
        addMountainPeaks(r);
        
        removeSingles();
        
    }

    private void IslandProtocoll(){
        int[] quadraticWaterSum = {0, 0};
        int N = 0;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if(getTerrainName(x, y).equals("water")){
                    quadraticWaterSum[0] += x*x;
                    quadraticWaterSum[1] += y*y;
                    N++;
                }
            }
        }
        quadraticWaterSum[0] = (int) Math.sqrt(quadraticWaterSum[0]/N);
        quadraticWaterSum[1] = (int) Math.sqrt(quadraticWaterSum[1]/N);
        createIsland(quadraticWaterSum[0], quadraticWaterSum[1], (float) 0.5);
    }
   
    
    private void addMountainPeaks(Random r) {
        double direction = -0.8;
        int starty = -height;
        boolean place_peaks = true;
        while (starty < 2*height){
            for(int x = 0; x < width; x++){
                double wiggle = r.nextDouble(0.27, 0.29);
                System.out.println(wiggle);
                //wiggle = 0.28;
                int y = (int) (starty-Math.sin(direction)*x+Math.sin(wiggle*x)*2.5);
                if(y < 0 || y >= height){
                    continue;
                }
                if (!"light_mountain".equals(this.getTerrainName(x, y))){
                    continue;
                }
                if(place_peaks){
                    setT(x, y, "peak_mountain");
                    if(y < height-1) setT(x,y+1,"peak_mountain");
                    if(y < 0) setT(x,y-1,"peak_mountain");
                }
                else{
                    setT(x, y, "forest");
                    if(y<height-1) setT(x, y+1, "forest");
                    if(y>=1) setT(x, y-1, "forest");
                }
            }
            starty += r.nextInt(5, 10);
            place_peaks = !place_peaks;
        }
        
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height-1; y++) {
                if(getTerrainName(x, y).equals("peak_mountain"))
                darkenAllMountain(x, y-1);
            }
        }
    }
    
    private void darkenAllMountain(int x, int y){
        if(x<0 || x>width-1 || y<0 || y>height-1 || !getTerrainName(x, y).equals("light_mountain")){
            return;
        }
        setT(x, y, "dark_mountain");
        darkenAllMountain(x+1, y);
        darkenAllMountain(x, y-1);
        darkenAllMountain(x-1, y);
    }
    
    private void createIsland(int xcoord, int ycoord, float range){
        Random r = new Random();
        spray(xcoord-25+r.nextInt(50), ycoord-25+r.nextInt(50), range, "forest");
        removeSingles();
        String[] forests = {"forest"};
        String[] forestngrass = {"forest", "grass"};
        Pruning("water", 1, forests, "grass");
        Pruning("water", 1, forestngrass, "desert");
        Pruning("grass", 4, forests, "forest");
        Pruning("desert", 4, forests, "desert");
    }
    
    /*
        löscht alle toReplace Terrains auf der Map, sobald diese an limit oder mehr des triggerTerrains grenzen
    */
    
    public void Pruning(String toReplace, int limit, String[] triggerTerrain, String toPlace){
        ArrayList<ArrayList<Integer>> tempList = new ArrayList<>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                ArrayList<Integer> localList = new ArrayList<>();
                if (this.getTerrainName(x, y).equals(toReplace) && getSurroundingTerrains(x, y, triggerTerrain) >= limit) {
                    localList.add(x);
                    localList.add(y);
                    tempList.add(localList);
                }
            }
        }
        this.replace(tempList, toPlace);
    }
    
    public int getSurroundingTerrains(int x, int y, String[] Terrains){
        int result = 0;
        for(int i = 0; i <= Terrains.length-1; i++){
            result += getSurroundingTerrain(x, y, Terrains[i]);
        }
        return result;
    }
    
    public int getSurroundingTerrain(int x, int y, String name) {
        int counter = 0;
        if (x != 0 && this.getTerrainName(x - 1, y).equals(name))
            counter++;
        if (y != 0 && this.getTerrainName(x, y - 1).equals(name))
            counter++;
        if (x != 0 && y != 0 && this.getTerrainName(x - 1, y - 1).equals(name))
            counter++;
        if (x < width - 1 && this.getTerrainName(x + 1, y).equals(name))
            counter++;
        if (y < height - 1 && this.getTerrainName(x, y + 1).equals(name))
            counter++;
        if (x < width - 1 && y != 0 && this.getTerrainName(x + 1, y - 1).equals(name))
            counter++;
        if (y < height - 1 && x != 0 && this.getTerrainName(x - 1, y + 1).equals(name))
            counter++;
        if (y < height - 1 && x < width - 1 && this.getTerrainName(x + 1, y + 1).equals(name))
            counter++;

        return counter;
    }
    
    public void removeSingles(){
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if(isAlone(x, y)){
                    this.setT(x, y, mostcommonNeighbor(x, y));
                }
            }
        }
    }
    
    public String mostcommonNeighbor(int x, int y){
        String[] ts = {"water", "desert", "grass", "forest"};
        int max = 0;
        String MaximumName = "water";
        for(int i = 0; i < ts.length; i++){
            if(getSurroundingTerrain(x, y, ts[i]) > max){
                MaximumName = ts[i];
                max = getSurroundingTerrain(x, y, ts[i]);
            }
        }
        return MaximumName;
    }
    
    public boolean isAlone(int x, int y){
        return getSurroundingTerrain(x, y, getTerrainName(x, y)) <= 1;
    }
    
    public int getAmountofTerrain(String name){
        int counter = 0;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if(this.getTerrainName(x, y).equals(name)) counter++;
            }
        }
        return counter;
    }

    public void replace(List<ArrayList<Integer>> coordinates, String terrainNametoPlace) {
        for (ArrayList<Integer> v : coordinates) {
            this.setT(v.get(0), v.get(1), terrainNametoPlace);
        }
    }

    public void setT(int xcoord, int ycoord, String terrainName) {
        felder.get(xcoord).remove(ycoord);
        felder.get(xcoord).add(ycoord, new Feld(new Terrain(terrainName),0,xcoord,ycoord));
    }
    
    
    /*
    Sprays Terrain on the map
    in round shape with random distribution
    */
    
    private void spray(int xcoord, int ycoord, float range, String terrainName){
        Random r = new Random();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int distancesq = (xcoord-x)*(xcoord-x) + (ycoord-y)*(ycoord-y);
                if(x == xcoord && y == ycoord){
                    this.setT(xcoord, ycoord, terrainName);
                    continue;
                }
                if(range*r.nextInt(100)/(distancesq)>1){
                    this.setT(x, y, terrainName);
                }
            }
        }
    } 

    public Image getTerrainPicture(int xcoord, int ycoord) {
        return getFeld(xcoord, ycoord).getT().getPicture();
    }
}
