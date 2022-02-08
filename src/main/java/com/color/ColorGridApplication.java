package com.color;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ColorGridApplication {

	public static void main(String[] args) {
		ColorGrid colorGrid = new ColorGrid(10,10);
		colorGrid.fillColor();
		colorGrid.printColorsGrid();
		colorGrid.searchContinuesColors();

		//SpringApplication.run(ColorGridApplication.class, args);
	}

}
