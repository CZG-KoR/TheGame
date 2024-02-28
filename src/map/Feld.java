package map;

public class Feld {
    // Terrain, Beschaffen des Feldes
    private Terrain t;

    // Höhe des Feldes, (Flat, Hilly, Mountainous)
    private int height;

    // Besetzt?
    private boolean occupied;
    
    //wurde Feld bereits gecheckt
    private boolean checked;

    // möglich da hin zu bewegen, nachdem Figur angeklickt wurde
    private boolean highlighted;
    private String occupiedby;

    // Position
    private int xPosition;
    private int yPosition;

    public Feld(Terrain t, int height, int xPosition, int yPosition) {
        this.t = t;
        this.height = height;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        occupied = false;
        checked=false;
        occupiedby=null;
    }

    public Terrain getT() {
        return t;
    }

    public String getTerrainName() {
        return t.getName();
    }

    public int getHeight() {
        return height;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getOccupiedby() {
        return occupiedby;
    }

    public void setOccupiedby(String occupiedby) {
        this.occupiedby = occupiedby;
    }

    //Testzwecke, muss eigentlich wieder entfernt werden
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
    
    

    public boolean isHighlighted() {
        return highlighted;
    }

    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public void setT(String terrainName) {
        this.t = new Terrain(terrainName);
    }

}
