package Vehicles;

public abstract class FallingObject {
    private boolean grounded;
    private boolean jumping;
    private float gravity;
    private float jumpPower;
    private float fallSpeed;

    public FallingObject() {
        this(0.2f);
    }

    public FallingObject(float gravity) {
        this(gravity, 2.0f);
    }

    public FallingObject(float gravity, float jumpPower) {
        this.gravity = gravity;
        this.jumpPower = jumpPower;
        grounded = true;
        jumping = false;
        fallSpeed = 0;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    void setJumpPower(float jumpPower) {
        this.jumpPower = jumpPower;
    }

    void setGrounded(boolean grounded) {
        this.grounded = grounded;
        if(grounded)
            jumping = false;
    }

    boolean isGrounded() {
        return grounded;
    }

    void jump() {
        jumping = true;
        grounded = false;
    }

    float gravity() {
        if(grounded) {
            fallSpeed = 0;
        } else {
            if(jumping) {
                fallSpeed -= (jumpPower - gravity);
                jumpPower -= gravity;
            } else {
                fallSpeed += gravity;
            }
        }
        return fallSpeed;
    }
}
