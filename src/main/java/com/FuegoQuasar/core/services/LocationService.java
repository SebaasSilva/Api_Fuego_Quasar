package com.FuegoQuasar.core.services;

import org.springframework.stereotype.Service;

import com.FuegoQuasar.core.model.Position;

@Service
public class LocationService {

    private Position kenobiPos = new Position();
    private Position skywalkerPos = new Position();
    private Position satoPos = new Position();

    public Position getLocation(Float kenobiDistance, Float skywalkerDistance, Float satoDistance) {

	Position starchipPosition = new Position();
	setPositions();

	// Logic for get Starchip position
	Float a = ((-2) * (kenobiPos.getX()) + (2 * (skywalkerPos.getX())));
	Float b = ((-2) * (kenobiPos.getY()) + (2 * (skywalkerPos.getY())));

	Float c = ((float) (Math.pow(kenobiDistance, 2))) - ((float) (Math.pow(skywalkerDistance, 2)))
		- ((float) (Math.pow(kenobiPos.getX(), 2))) + ((float) (Math.pow(skywalkerPos.getX(), 2)))
		- ((float) (Math.pow(kenobiPos.getY(), 2))) + ((float) (Math.pow(skywalkerPos.getY(), 2)));

	Float d = ((-2) * (skywalkerPos.getX()) + (2 * (satoPos.getX())));
	Float e = ((-2) * (skywalkerPos.getY()) + (2 * (satoPos.getY())));

	Float f = ((float) (Math.pow(skywalkerDistance, 2))) - ((float) (Math.pow(satoDistance, 2)))
		- ((float) (Math.pow(skywalkerPos.getX(), 2))) + ((float) (Math.pow(satoPos.getX(), 2)))
		- ((float) (Math.pow(skywalkerPos.getY(), 2))) + ((float) (Math.pow(satoPos.getY(), 2)));
	//
	starchipPosition.setX(((c * e) - f * b) / ((e * a) - (b * d)));
	starchipPosition.setY(((c * d) - a * f) / ((b * d) - (a * e)));
	return starchipPosition;
    }

    public Position getSimpleLocation(Float distance, String sateliteName) {
	try {
	    setPositions();
	    if (sateliteName.equals("kenobi")) {
		return this.kenobiPos;
	    } else if (sateliteName.equals("skywalker")) {
		return this.skywalkerPos;
	    } else if (sateliteName.equals("sato")) {
		return this.satoPos;
	    }

	    return new Position();

	} catch (Exception ex) {
	    ex.printStackTrace();
	    return new Position();
	}

    }

    private void setPositions() {

	this.kenobiPos.setX(-500f);
	this.kenobiPos.setY(-200f);
	this.skywalkerPos.setX(100f);
	this.skywalkerPos.setY(-100f);
	this.satoPos.setX(500f);
	this.satoPos.setY(100f);

    }
}
