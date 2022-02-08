package com.color;

import lombok.*;

import java.awt.*;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ColorGrid {
    private int x;
    private int y;
    private Color[][] colorArray;// new Color[x][y];
    private Map<String,Integer> valueMap = new HashMap<>();
    private HashSet<String> colors = new HashSet<>();
    private int maxSize = 0;

    public ColorGrid(int x, int y){
        this.x = x;
        this.y = y;
        this.colorArray = new Color[x][y];
    }


    public void fillColor(){
        Random rand = new Random();
        ColorUtils colorUtils = new ColorUtils();
        for(int i = 0; i < x ; i++ ){
            for(int j = 0; j < y ; j++ ){
                int r = rand.nextInt(100);
                int g = rand.nextInt(100);
                int b = rand.nextInt(100);
                colorArray[i][j] = new Color(r,g,b);
                String color = colorName(colorArray[i][j]);
                colors.add(color);
                if(color.length() > maxSize){
                    maxSize = color.length();
                }
            }
        }
    }

    public void printColorsGrid(){
        for(int row = 0; row < x ; row++ ){
            for(int column = 0; column < y ; column++ ){
                Color c = colorArray[row][column];
                String colorName = colorName(c);
                System.out.print(colorName);
                int space = maxSize - colorName.length() + 2;
                while (space > 0){
                    System.out.print(" ");
                    space -= 1;
                }
            }
            System.out.println("");
        }
    }

    public void searchContinuesColors(){
        searchRow();
        searchColumn();
        System.out.println("");
        System.out.println("Combinations >> ");
        for (String color: valueMap.keySet()){
            System.out.println(color + " ---->>> " + valueMap.get(color) );
        }


    }

    private void searchRow(){
        for(int row = 0; row < x ; row++ ){
            for(int column = 0; column < y ; column++ ){
                Color c = colorArray[row][column];
                int size = 1;
                String colorName = colorName(c);
                for(int nextColumn = column + 1; nextColumn < y; nextColumn++){
                    String adjacentNodeColor = colorName(colorArray[row][nextColumn]);
                   // column = nextColumn;
                    if(colorName.equalsIgnoreCase(adjacentNodeColor)){
                        size += 1;
                    }else {
                        break;
                    }
                }
                addToMap(colorName, size);
            }
        }
    }

    private void searchColumn(){
        for(int column = 0; column < y ; column++ ){
            for(int row = 0; row < x ; row++ ){
                Color c = colorArray[row][column];
                int size = 1;
                String colorName = colorName(c);
                for(int nextRow = row + 1; nextRow < x; nextRow++){
                    String adjacentNodeColor = colorName(colorArray[nextRow][column]);
                    // column = nextColumn;
                    if(colorName.equalsIgnoreCase(adjacentNodeColor)){
                        size += 1;
                    }else {
                        break;
                    }
                }
                addToMap(colorName, size);
            }
        }
    }

    private void addToMap(String colorName, int size){
        if (valueMap.get(colorName) != null){
            if(Integer.parseInt(valueMap.get(colorName).toString()) < size){
                valueMap.put(colorName, size);
            }
        }else {
            valueMap.put(colorName, size);
        }
    }

    private String colorName(Color c){
        ColorUtils colorUtils = new ColorUtils();
        return colorUtils.getColorNameFromColor(c);
    }
}
