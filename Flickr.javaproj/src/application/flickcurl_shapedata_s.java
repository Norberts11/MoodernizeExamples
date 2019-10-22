package application;

public class flickcurl_shapedata_s {
	private int created;
	private double alpha;
	private int points;
	private int edges;
	private byte[] data;
	private Object data_length;
	private byte[][] file_urls;
	private int file_urls_count;
	private int is_donuthole;
	private int has_donuthole;
	
	public flickcurl_shapedata_s(int created, double alpha, int points, int edges, byte[] data, Object data_length, byte[][] file_urls, int file_urls_count, int is_donuthole, int has_donuthole) {
		setCreated(created);
		setAlpha(alpha);
		setPoints(points);
		setEdges(edges);
		setData(data);
		setData_length(data_length);
		setFile_urls(file_urls);
		setFile_urls_count(file_urls_count);
		setIs_donuthole(is_donuthole);
		setHas_donuthole(has_donuthole);
	}
	public flickcurl_shapedata_s() {
	}
	
	public void command_print_shape(flickcurl_shapedata_s[] shape) {
		int generatedCreated = shape.getCreated();
		double generatedAlpha = shape.getAlpha();
		int generatedPoints = shape.getPoints();
		int generatedEdges = shape.getEdges();
		int generatedIs_donuthole = shape.getIs_donuthole();
		int generatedHas_donuthole = shape.getHas_donuthole();
		.fprintf((_iob[1]), "created %d  alpha %2.2f  #points %d  #edges %d\n  is donuthole: %d  has donuthole: %d\n", generatedCreated, generatedAlpha, generatedPoints, generatedEdges, generatedIs_donuthole, generatedHas_donuthole);
		Object generatedData_length = shape.getData_length();
		byte[] generatedData = shape.getData();
		if (generatedData_length > 0) {
			int s;
			s = (generatedData_length > 70 ? 70 : generatedData_length);
			.fprintf((_iob[1]), "  Shapedata (%d bytes):\n    ", (int)generatedData_length);
			.fwrite(generatedData, 1, s, (_iob[1]));
			.fputs("...\n", (_iob[1]));
		} 
		int generatedFile_urls_count = shape.getFile_urls_count();
		byte[][] generatedFile_urls = shape.getFile_urls();
		if (generatedFile_urls_count > 0) {
			int j;
			.fprintf((_iob[1]), "  Shapefile URLs: %d\n", generatedFile_urls_count);
			for (j = 0; j < generatedFile_urls_count; j++) {
				.fprintf((_iob[1]), "    URL %d: %s\n", j, generatedFile_urls[j]);
			}
		} 
	}
	/* -*- Mode: c; c-basic-offset: 2 -*-
	 *
	 * shape.c - Flickr shape support calls
	 *
	 * Copyright (C) 2009, David Beckett http://www.dajobe.org/
	 * 
	 * This file is licensed under the following three licenses as alternatives:
	 *   1. GNU Lesser General Public License (LGPL) V2.1 or any newer version
	 *   2. GNU General Public License (GPL) V2 or any newer version
	 *   3. Apache License, V2.0 or any newer version
	 * 
	 * You may not use this file except in compliance with at least one of
	 * the above three licenses.
	 * 
	 * See LICENSE.html or LICENSE.txt at the top of this package for the
	 * complete terms and further detail along with the license texts for
	 * the licenses in COPYING.LIB, COPYING and LICENSE-2.0.txt respectively.
	 * 
	 */
	/**
	 * flickcurl_free_shape:
	 * @shape: shape object
	 *
	 * Destructor for shape object
	 */
	public void flickcurl_free_shape(flickcurl_shapedata_s[] shape) {
		int i;
		do {
			if (!shape) {
				.fprintf((_iob[2]), "%s:%d: (%s) assertion failed: object pointer of type flickcurl_shapedata is NULL.\n", "E:\\Programfiles\\Eclipse\\Workspaces\\runtime-EclipseApplication\\Flickr\\src\\shape.c", 56, __func__);
				return ;
			} 
		} while (0);
		byte[] generatedData = shape.getData();
		if (generatedData) {
			.free(generatedData);
		} 
		byte[][] generatedFile_urls = shape.getFile_urls();
		int generatedFile_urls_count = shape.getFile_urls_count();
		if (generatedFile_urls) {
			for (i = 0; i < generatedFile_urls_count; i++) {
				.free(generatedFile_urls[i]);
			}
			.free(generatedFile_urls);
		} 
		.free(shape/**
		 * flickcurl_free_shapes:
		 * @shapes_object: shape object array
		 *
		 * Destructor for array of shape object
		 */);
	}
	public void flickcurl_free_shapes(flickcurl_shapedata_s[][] shapes_object) {
		int i;
		do {
			if (!shapes_object) {
				.fprintf((_iob[2]), "%s:%d: (%s) assertion failed: object pointer of type flickcurl_shapedata_array is NULL.\n", "E:\\Programfiles\\Eclipse\\Workspaces\\runtime-EclipseApplication\\Flickr\\src\\shape.c", 82, __func__);
				return ;
			} 
		} while (0);
		flickcurl_shapedata_s flickcurl_shapedata_s = new flickcurl_shapedata_s();
		for (i = 0; shapes_object[i]; i++) {
			flickcurl_shapedata_s.flickcurl_free_shape(shapes_object[i]);
		}
		.free(shapes_object);
	}
	/* get shapedata from value */
	public flickcurl_shapedata_s[][] flickcurl_build_shapes(flickcurl_s[] fc, Object xpathCtx, Object[] xpathExpr, Integer shape_count_p) {
		flickcurl_shapedata[][] shapes = ((Object)0);
		int nodes_count;
		int shape_count;
		 xpathObj = ((Object)0);
		 nodes = new ();
		int i;
		xpathObj = .xmlXPathEvalExpression(xpathExpr, xpathCtx);
		flickcurl_s flickcurl_s = new flickcurl_s();
		if (!xpathObj) {
			flickcurl_s.flickcurl_error(fc, "Unable to evaluate XPath expression \"%s\"", xpathExpr);
			fc.setFailed(1);
			;
		} 
		nodes = xpathObj.getNodesetval();
		nodes_count = .xmlXPathNodeSetGetLength(/* This is a max size - it can include nodes that are CDATA */nodes);
		shapes = (flickcurl_shapedata).calloc(, nodes_count + 1);
		Object generatedData_length = shape.getData_length();
		flickcurl_s flickcurl_s = new flickcurl_s();
		flickcurl_s flickcurl_s = new flickcurl_s();
		int generatedFile_urls_count = shape.getFile_urls_count();
		byte[][] generatedFile_urls = shape.getFile_urls();
		int generatedFailed = fc.getFailed();
		if (shape_count_p) {
			shape_count_p = shape_count;
		} 
		flickcurl_shapedata_s flickcurl_shapedata_s = new flickcurl_shapedata_s();
		if (generatedFailed) {
			if (shapes) {
				flickcurl_shapedata_s.flickcurl_free_shapes(shapes);
			} 
			shapes = ((Object)0);
		} 
		return shapes;
	}
	public flickcurl_shapedata_s[] flickcurl_build_shape(flickcurl_s[] fc, Object xpathCtx, Object[] xpathExpr) {
		flickcurl_shapedata[][] shapes = new flickcurl_shapedata();
		flickcurl_shapedata[] result = ((Object)0);
		flickcurl_shapedata_s flickcurl_shapedata_s = new flickcurl_shapedata_s();
		shapes = flickcurl_shapedata_s.flickcurl_build_shapes(fc, xpathCtx, xpathExpr, ((Object)0));
		if (shapes) {
			result = shapes[0];
			.free(shapes);
		} 
		return result;
	}
	public flickcurl_shapedata_s[][] flickcurl_places_getShapeHistory(flickcurl_s[] fc, Object[] place_id, int woe_id) {
		 doc = ((Object)0);
		 xpathCtx = ((Object)0);
		flickcurl_shapedata[][] shapes = ((Object)0);
		byte[] woe_id_str = new byte[20];
		flickcurl_s flickcurl_s = new flickcurl_s();
		flickcurl_s.flickcurl_init_params(fc, 0);
		if (!place_id && woe_id < 0) {
			return ((Object)0);
		} 
		flickcurl_s flickcurl_s = new flickcurl_s();
		if (place_id) {
			flickcurl_s.flickcurl_add_param(fc, "place_id", place_id);
		} 
		if (woe_id >= 0) {
			.sprintf(woe_id_str, "%d", woe_id);
			flickcurl_s.flickcurl_add_param(fc, "woe_id", woe_id_str);
		} 
		flickcurl_s flickcurl_s = new flickcurl_s();
		flickcurl_s.flickcurl_end_params(fc);
		flickcurl_s flickcurl_s = new flickcurl_s();
		if (flickcurl_s.flickcurl_prepare(fc, "flickr.places.getShapeHistory")) {
			;
		} 
		flickcurl_s flickcurl_s = new flickcurl_s();
		doc = flickcurl_s.flickcurl_invoke(fc);
		if (!doc) {
			;
		} 
		xpathCtx = .xmlXPathNewContext(doc);
		flickcurl_s flickcurl_s = new flickcurl_s();
		if (!xpathCtx) {
			flickcurl_s.flickcurl_error(fc, "Failed to create XPath context for document");
			fc.setFailed(1);
			;
		} 
		flickcurl_shapedata_s flickcurl_shapedata_s = new flickcurl_shapedata_s();
		shapes = flickcurl_shapedata_s.flickcurl_build_shapes(fc, xpathCtx, ()"/rsp/shapes/shapedata|/rsp/shapes/shape", ((Object)0));
		int generatedFailed = fc.getFailed();
		flickcurl_shapedata_s flickcurl_shapedata_s = new flickcurl_shapedata_s();
		if (generatedFailed) {
			if (shapes) {
				flickcurl_shapedata_s.flickcurl_free_shapes(shapes);
			} 
			shapes = ((Object)0);
		} 
		return shapes/**
		 * flickcurl_places_getTopPlacesList:
		 * @fc: flickcurl context
		 * @place_type: The place type to cluster photos by. Valid place types are : neighbourhood, locality, region, country and continent
		 * @date: A valid date in YYYY-MM-DD format. The default is yesterday. (or NULL)
		 * @woe_id: Limit your query to only those top places belonging to a specific Where on Earth (WOE) identifier. (or NULL)
		 * @place_id: Limit your query to only those top places belonging to a specific Flickr Places identifier. (or NULL)
		 * 
		 * Return the top 100 most geotagged places for a day.
		 *
		 * Implements flickr.places.getTopPlacesList (1.12)
		 * 
		 * Return value: array of places or NULL on failure
		 **/;
	}
	public int getCreated() {
		return created;
	}
	public void setCreated(int newCreated) {
		created = newCreated;
	}
	public double getAlpha() {
		return alpha;
	}
	public void setAlpha(double newAlpha) {
		alpha = newAlpha;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int newPoints) {
		points = newPoints;
	}
	public int getEdges() {
		return edges;
	}
	public void setEdges(int newEdges) {
		edges = newEdges;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] newData) {
		data = newData;
	}
	public Object getData_length() {
		return data_length;
	}
	public void setData_length(Object newData_length) {
		data_length = newData_length;
	}
	public byte[][] getFile_urls() {
		return file_urls;
	}
	public void setFile_urls(byte[][] newFile_urls) {
		file_urls = newFile_urls;
	}
	public int getFile_urls_count() {
		return file_urls_count;
	}
	public void setFile_urls_count(int newFile_urls_count) {
		file_urls_count = newFile_urls_count;
	}
	public int getIs_donuthole() {
		return is_donuthole;
	}
	public void setIs_donuthole(int newIs_donuthole) {
		is_donuthole = newIs_donuthole;
	}
	public int getHas_donuthole() {
		return has_donuthole;
	}
	public void setHas_donuthole(int newHas_donuthole) {
		has_donuthole = newHas_donuthole;
	}
}
/**
 * flickcurl_arg:
 * @name: Argument name
 * @optional: boolean flag (non-0 true) if argument is optional
 * @description: description of argument (HTML)
 *
 * An API method argument.
 */
