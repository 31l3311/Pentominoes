import javafx.application.Application;

import static javafx.application.Application.launch;

import java.util.Arrays;


import javafx.geometry.Point3D;
import javafx.scene.DepthTest;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.scene.shape.DrawMode; 

public class BoxExample2 extends Application {
    final Group root = new Group();
    final XformWorld world = new XformWorld();
    final PerspectiveCamera camera = new PerspectiveCamera(true);
    final XformCamera cameraXform = new XformCamera();
    public static final double CAMERA_INITIAL_DISTANCE = -1000;
    public static final double CAMERA_NEAR_CLIP = 0.1;
    public static final double CAMERA_FAR_CLIP = 10000.0;
    double mousePosX, mousePosY, mouseOldX, mouseOldY, mouseDeltaX, mouseDeltaY;
    double mouseFactorX, mouseFactorY;
    private int amountOfA, amountOfB, amountOfC;



    @Override
    public void start(Stage primaryStage) {
        root.getChildren().add(world);
        root.setDepthTest(DepthTest.ENABLE);
        buildCamera();
        buildBodySystem();
        Scene scene = new Scene(root, 1600, 1000, true);
        scene.setFill(Color.GREY);
        handleMouse(scene);
        primaryStage.setTitle("BoxExample");
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.setCamera(camera);
        mouseFactorX = 180.0 / scene.getWidth();
        mouseFactorY = 180.0 / scene.getHeight();
     }

    public void buildCamera() {
        root.getChildren().add(cameraXform);
        cameraXform.getChildren().add(camera);
        camera.setNearClip(CAMERA_NEAR_CLIP);
        camera.setFarClip(CAMERA_FAR_CLIP);
        camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
        
    }

    public void setBoxAmounts(int amountOfA, int amountOfB, int amountOfC) {
        this.amountOfA = amountOfA;
        this.amountOfB = amountOfB;
        this.amountOfC = amountOfC;
    }

    public void buildBodySystem() {
        Boxes[] boxes = {new Boxes("A", 3, amountOfA), new Boxes("B", 4, amountOfB), new Boxes("C", 5, amountOfC)};

	    Truck truck = new Truck(33, 8, 5);

	    RandomB algorithm = new RandomB(boxes, truck);
	    algorithm.solve();

	    int v = truck.space.length * truck.space[0].length * truck.space[0][0].length;
	    int [] truck1D = new int [v];
	    int l = 0;
	    for (int q = 0; q < truck.space.length ; q++)
	    	for (int w = 0; w < truck.space[0].length ; w++)
	    		for (int e = 0; e < truck.space[0][0].length; e++){ 			 
	    			truck1D[l++] = truck.space[q][w][e];
	    		}	  
        double x = -10;
        double y = -17.5;
        double z = -80;
        Box box = new Box(25, 40, 165);
        box.setDrawMode(DrawMode.LINE);
        box.setTranslateX(0.0);
        box.setTranslateY(0.0);
        box.setTranslateZ(0.0);


        for(int i = 0 ; i < truck1D.length; i++ ) {
            Box a = new Box(5.0,5.0,5.0);
            if (truck1D[i] == 5) {
                PhongMaterial GreenMaterial = new PhongMaterial();
                GreenMaterial.setDiffuseColor(Color.GREEN);
                GreenMaterial.setSpecularColor(Color.DARKRED);
                a.setMaterial(GreenMaterial);
                a.setTranslateX(x);
                a.setTranslateY(y);
                a.setTranslateZ(z);
                world.getChildren().addAll(a);
                x = x + 5;
                if (x > 10) {
                	x = -10;
                	y = y +5;
                }
                if (y > 17.5) {
               	 x = -10;
               	 y = -17.5;
               	 z = z + 5;
                }
            }
            if (truck1D[i] == 4) {           	
            	 PhongMaterial RedMaterial = new PhongMaterial();
                 RedMaterial.setDiffuseColor(Color.RED);
                 RedMaterial.setSpecularColor(Color.DARKRED);
                 a.setMaterial(RedMaterial);
                 a.setTranslateX(x);
                 a.setTranslateY(y);
                 a.setTranslateZ(z);
                 world.getChildren().addAll(a);
                 x = x + 5;
                 if (x > 10) {
                	 x = -10;
                	 y = y + 5;
                 }
                 if (y > 17.5) {
                	 x = -10;
                	 y = -17.5;
                	 z = z + 5;
                 }
             }
            if (truck1D[i] == 3) {           	
           	 PhongMaterial RedMaterial = new PhongMaterial();
                RedMaterial.setDiffuseColor(Color.BLUE);
                RedMaterial.setSpecularColor(Color.DARKRED);
                a.setMaterial(RedMaterial);
                a.setTranslateX(x);
                a.setTranslateY(y);
                a.setTranslateZ(z);
                world.getChildren().addAll(a);
                x = x + 5;
                if (x > 10) {
               	 x = -10;
               	 y = y + 5;
                }
                if (y > 17.5) {
               	 x = -10;
               	 y = -17.5;
               	 z = z + 5;
                }
            }
            
            if (truck1D[i] == 0) {
            	PhongMaterial RedMaterial = new PhongMaterial();
                RedMaterial.setDiffuseColor(Color.GREY);
                RedMaterial.setSpecularColor(Color.DARKRED);
                a.setDrawMode(DrawMode.LINE);
                a.setMaterial(RedMaterial);
                a.setTranslateX(x);
                a.setTranslateY(y);
                a.setTranslateZ(z);
                world.getChildren().addAll(a);
                x = x + 5;
                if (x > 10) {
               	 x = -10;
               	 y = y + 5;
                }
                if (y > 17.5) {
               	 x = -10;
               	 y = -17.5;
               	 z = z + 5;
                }
            }
            

        }

        world.getChildren().addAll(box);
        
    }
    
    /*public void buildBodySystemG() {
	    Boxes[] boxes = {new Boxes("A", 3, 0), new Boxes("B", 4, 1000), new Boxes("C", 5, 1000)};

	    Truck truck = new Truck(33, 8, 5);

	    GreedyB truckArray = new GreedyB(boxes, truck);
	    truckArray.solve();

	    int v = truck.space.length * truck.space[0].length * truck.space[0][0].length;
	    int [] truck1D = new int [v];
	    int l = 0;
	    for (int q = 0; q < truck.space.length ; q++)
	    	for (int w = 0; w < truck.space[0].length ; w++)
	    		for (int e = 0; e < truck.space[0][0].length; e++){ 			 
	    			truck1D[l++] = truck.space[q][w][e];
	    		}	
	    System.out.println(Arrays.toString(truck1D));
        double x = -10;
        double y = -17.5;
        double z = -80;
        Boxes box = new Boxes(25, 40, 165);
        box.setDrawMode(DrawMode.LINE);
        box.setTranslateX(0.0);
        box.setTranslateY(0.0);
        box.setTranslateZ(0.0);


        for(int i = 0 ; i < truck1D.length; i++ ) {
            Boxes a = new Boxes(5.0,5.0,5.0);
            if (truck1D[i] == 5) {
                PhongMaterial GreenMaterial = new PhongMaterial();
                GreenMaterial.setDiffuseColor(Color.GREEN);
                GreenMaterial.setSpecularColor(Color.DARKRED);
                a.setMaterial(GreenMaterial);
                a.setTranslateX(x);
                a.setTranslateY(y);
                a.setTranslateZ(z);
                world.getChildren().addAll(a);
                x = x + 5;
                if (x > 10) {
                	x = -10;
                	y = y +5;
                }
                if (y > 17.5) {
               	 x = -10;
               	 y = -17.5;
               	 z = z + 5;
                }
            }
            if (truck1D[i] == 4) {           	
            	 PhongMaterial RedMaterial = new PhongMaterial();
                 RedMaterial.setDiffuseColor(Color.RED);
                 RedMaterial.setSpecularColor(Color.DARKRED);
                 a.setMaterial(RedMaterial);
                 a.setTranslateX(x);
                 a.setTranslateY(y);
                 a.setTranslateZ(z);
                 world.getChildren().addAll(a);
                 x = x + 5;
                 if (x > 10) {
                	 x = -10;
                	 y = y + 5;
                 }
                 if (y > 17.5) {
                	 x = -10;
                	 y = -17.5;
                	 z = z + 5;
                 }
             }
            if (truck1D[i] == 3) {           	
           	 PhongMaterial RedMaterial = new PhongMaterial();
                RedMaterial.setDiffuseColor(Color.BLUE);
                RedMaterial.setSpecularColor(Color.DARKRED);
                a.setMaterial(RedMaterial);
                a.setTranslateX(x);
                a.setTranslateY(y);
                a.setTranslateZ(z);
                world.getChildren().addAll(a);
                x = x + 5;
                if (x > 10) {
               	 x = -10;
               	 y = y + 5;
                }
                if (y > 17.5) {
               	 x = -10;
               	 y = -17.5;
               	 z = z + 5;
                }
            }
            
            if (truck1D[i] == 0) {
            	PhongMaterial RedMaterial = new PhongMaterial();
                RedMaterial.setDiffuseColor(Color.GREY);
                RedMaterial.setSpecularColor(Color.DARKRED);
                a.setDrawMode(DrawMode.LINE);
                a.setMaterial(RedMaterial);
                a.setTranslateX(x);
                a.setTranslateY(y);
                a.setTranslateZ(z);
                world.getChildren().addAll(a);
                x = x + 5;
                if (x > 10) {
               	 x = -10;
               	 y = y + 5;
                }
                if (y > 17.5) {
               	 x = -10;
               	 y = -17.5;
               	 z = z + 5;
                }
            }
            

        }

        world.getChildren().addAll(box);
        
    }*/

    public void handleMouse(Scene scene) {
        scene.setOnMousePressed((MouseEvent me) -> {
            mousePosX = me.getSceneX();
            mousePosY = me.getSceneY();
            mouseOldX = me.getSceneX();
            mouseOldY = me.getSceneY();
            
        });
        scene.setOnMouseDragged((MouseEvent me) -> {
            mouseOldX = mousePosX;
            mouseOldY = mousePosY;
            mousePosX = me.getSceneX();
            mousePosY = me.getSceneY();
            mouseDeltaX = (mousePosX - mouseOldX);
            mouseDeltaY = (mousePosY - mouseOldY);
            if (me.isPrimaryButtonDown()) {
                cameraXform.ry(mouseDeltaX * 180.0 / scene.getWidth());
                cameraXform.rx(-mouseDeltaY * 180.0 / scene.getHeight());
            } else if (me.isSecondaryButtonDown()) {
                camera.setTranslateZ(camera.getTranslateZ() + mouseDeltaY);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
        
    }

}

