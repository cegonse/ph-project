package net.jumbledevs.phproject;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;

import javax.swing.text.AttributeSet;

public class PhProject extends ApplicationAdapter {
    private PerspectiveCamera main_camera;
    private ModelBatch model_batch;
    private ModelBuilder model_builder;
    private Model test_box;
    private ModelInstance box_instance;
    private Environment environment;

	@Override
	public void create () {
	    main_camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        main_camera.position.set(10f, 10f, 10f);
        main_camera.lookAt(0,0,0);
        main_camera.near = 1f;
        main_camera.far = 300f;
	    main_camera.update();

	    model_batch = new ModelBatch();
	    model_builder = new ModelBuilder();

	    test_box = model_builder.createBox(5f, 5f, 5f,
                new Material(ColorAttribute.createDiffuse(0.3f, 1.0f, 0.6f, 0.5f)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);

	    box_instance = new ModelInstance(test_box);

	    environment = new Environment();
	    environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.5f, 0.5f, 0.5f, 1f));
	    environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        box_instance.transform.rotate(new Vector3(0f, 1f, 0f), 1f);
        model_batch.begin(main_camera);

        model_batch.render(box_instance, environment);
        model_batch.end();
	}
	
	@Override
	public void dispose () {
	    model_batch.dispose();
    }
}
