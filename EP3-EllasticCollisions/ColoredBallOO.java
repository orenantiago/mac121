/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Renan Tiago dos Santos Silva
 * Numero USP: 9793606
 * Tarefa: Elastic collisions
 * Data: 10/08/2017
 *
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE,
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/

import java.awt.Color;

public class ColoredBallOO {

  private Vector position, velocity;
  private double radius;
  private Color color;

  public ColoredBallOO(Vector p, Vector v, double r, Color c) {
    position = p;
    velocity = v;
    radius = r;
    color = c;
  }

  public Vector pos() {
    return position;
  }

  public Vector vel() {
    return velocity;
  }

  public double radius() {
    return radius;
  }

  public void setVel(Vector v) {
    velocity = v;
  }

  private Vector getNextPosition(double dt) {
    return position.plus(velocity.scale(dt));
  }

  public void updatePosition(double dt) {
    position = getNextPosition(dt);
  }

  public void treatWalls(double size, double dt) {

    Vector nextposition = getNextPosition(dt);
    double vx = velocity.cartesian(0), vy = velocity.cartesian(1);

    if(nextposition.cartesian(0)  + radius >= size || nextposition.cartesian(0)  - radius <= 0)
      vx = -vx;

    if(nextposition.cartesian(1)  + radius >= size || nextposition.cartesian(1)  - radius <= 0)
      vy = -vy;

    velocity = new Vector(new double [] {vx, vy});
  }

  public void move(double size, double dt) {
    treatWalls(size, dt);
    updatePosition(dt);
  }

  public void draw() {
      StdDraw.setPenColor(color);
      StdDraw.filledCircle(position.cartesian(0), position.cartesian(1), radius);
  }
}
