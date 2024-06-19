package MuslimQibla.Classes;

public class Ayah {
    public String ayah;
    public String surah;
    public int surahNumber;
    public int ayahNumber;
    public int pageNumber;
    public boolean comparator(Ayah ayah){
        if (ayah.surahNumber > this.surahNumber){
            return true;
        }else if (ayah.surahNumber < this.surahNumber){
            return false;
        }else{
            if(ayah.ayahNumber > this.ayahNumber){
                return true;
            }else{
                return false;
            }
        }
    }
}
