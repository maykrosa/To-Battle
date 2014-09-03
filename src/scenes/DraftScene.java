package scenes;

import game.GamePanel;
import game.StaticContent;
import graphics.RectangleGL;

import java.util.ArrayList;

import managers.texture.TextureManager;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import utils.PVector;
import utils.RenderGL;

public class DraftScene extends Scene {

	private RectangleGL start = null;
	private RectangleGL collector = null;
	private RectangleGL melee = null;
	private RectangleGL range = null;
	private RectangleGL area = null;

	public int currenteHUD = 0;
	public static final int HUDCOLLECTOR = 1;
	public static final int HUDMELEE = 2;
	public static final int HUDRANGE = 3;
	public static final int HUDAREA = 4;

	private float offLeft;
	private float offTop;
	private float spacingX;

	private float widthCard;
	private float heightCard;

	private boolean isCollector = true;
	private boolean isMelee = false;
	private boolean isRange = false;
	private boolean isArea = false;

	private RectangleGL[] selectedCards;
	private RectangleGL[] cards;
	private ArrayList<Integer> indicePersonagens;

	public DraftScene(GamePanel parent) {
		this.parent = parent;

		position = new PVector(0, 0);
		zoom = 1f;

		width = parent.width;
		height = parent.height;

		offLeft = width * 0.07f;
		offTop = height * 0.15f;
		spacingX = width * 0.12f;
		widthCard = width * 0.12f;
		heightCard = height * 0.28f;

		selectedCards = new RectangleGL[4];
		cards = new RectangleGL[3];
		indicePersonagens = new ArrayList<>();

		start = new RectangleGL(TextureManager.read, width * 0.78f,
				height * 0.80f, width * 0.92f, height * 0.86f, 1);

		criaCardSelecionados();

		currenteHUD = HUDCOLLECTOR;
	}

	@Override
	public void keyboardInput() {
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				if (Keyboard.getEventKey() == Keyboard.KEY_F) {
					parent.setDisplayMode(width, height,
							!Display.isFullscreen());
				}
			} else {

			}
		}
	}

	public void criaCardSelecionados() {
		selectedCards[0] = new RectangleGL(TextureManager.cardBase,
				width * 0.72f, height * 0.15f, width * 0.72f + width * 0.12f,
				height * 0.15f + height * 0.23f, 1);

		selectedCards[1] = new RectangleGL(TextureManager.cardBase,
				width * 0.85f, height * 0.15f, width * 0.85f + width * 0.12f,
				height * 0.15f + height * 0.23f, 1);

		selectedCards[2] = new RectangleGL(TextureManager.cardBase,
				width * 0.72f, height * 0.39f, width * 0.72f + width * 0.12f,
				height * 0.4f + height * 0.23f, 1);

		selectedCards[3] = new RectangleGL(TextureManager.cardBase,
				width * 0.85f, height * 0.39f, width * 0.85f + width * 0.12f,
				height * 0.4f + height * 0.23f, 1);
	}

	int cont = 0;

	@Override
	public void mouseInput() {
		mouseX = Mouse.getX();
		mouseY = (Mouse.getY() - parent.height) * -1;
		while (Mouse.next()) {
			/* Mouse pressed */

			if (Mouse.getEventButtonState()) {
				if (Mouse.getEventButton() == StaticContent.RIGHT_BUTTON) {

				}
				if (Mouse.getEventButton() == StaticContent.LEFT_BUTTON) {

					for (int i = 0; i < 3; i++) {
						if (isCollector) {
							if (cards[i].contains(mouseX, mouseY)) {
								cont++;
								if (cont > 1) {
									indicePersonagens.remove(indicePersonagens
											.size() - 1);
								}
								selectedCards[0].setTexture(cards[i]
										.getTexture());
								indicePersonagens.add(i);
								System.out.println("cards" + i);
								System.out.println("indice"
										+ indicePersonagens.size());
								System.out.println("click " + cont);

							}
						}
						if (isMelee) {
							if (cards[i].contains(mouseX, mouseY)) {
								cont++;
								if (cont > 1) {
									indicePersonagens.remove(indicePersonagens
											.size() - 1);
								}
								selectedCards[1].setTexture(cards[i]
										.getTexture());
								indicePersonagens.add(i);

								System.out.println("cards" + i);
								System.out.println("indice"
										+ indicePersonagens.size());
								System.out.println("click " + cont);

							}
						}
						if (isRange) {
							if (cards[i].contains(mouseX, mouseY)) {
								cont++;
								if (cont > 1) {
									indicePersonagens.remove(indicePersonagens
											.size() - 1);
								}
								selectedCards[2].setTexture(cards[i]
										.getTexture());
								indicePersonagens.add(i);
								System.out.println("cards" + i);
								System.out.println("indice"
										+ indicePersonagens.size());
							}
						}
						if (isArea) {
							if (cards[i].contains(mouseX, mouseY)) {
								cont++;
								if (cont > 1) {
									indicePersonagens.remove(indicePersonagens
											.size() - 1);
								}
								selectedCards[3].setTexture(cards[i]
										.getTexture());
								indicePersonagens.add(i);
								System.out.println("cards" + i);
								System.out.println("indice"
										+ indicePersonagens.size());
							}
						}
					}

					if (start.contains(mouseX, mouseY)) {
						if (indicePersonagens.size() == 4) {
							for (int i = 0; i < indicePersonagens.size(); i++) {
								System.out.println("indice pesonagens :"
										+ indicePersonagens.get(i));
							}
							parent.currentScene = new GamePlayScene(parent);
						}

					}
					if (collector.contains(mouseX, mouseY)) {
						currenteHUD = HUDCOLLECTOR;
						isCollector = true;
						isMelee = false;
						isRange = false;
						isArea = false;
						cont = 0;
					}
					if (melee.contains(mouseX, mouseY)) {
						currenteHUD = HUDMELEE;
						isCollector = false;
						isMelee = true;
						isRange = false;
						isArea = false;
						cont = 0;
					}
					if (range.contains(mouseX, mouseY)) {
						currenteHUD = HUDRANGE;
						isCollector = false;
						isMelee = false;
						isRange = true;
						isArea = false;
						cont = 0;
					}
					if (area.contains(mouseX, mouseY)) {
						currenteHUD = HUDAREA;
						isCollector = false;
						isMelee = false;
						isRange = false;
						isArea = true;
						cont = 0;
					}

				}

				if (Mouse.getEventButton() == StaticContent.MIDDLE_BUTTON) {

				}
				/* Mouse release */
			} else {
				if (Mouse.getEventButton() == StaticContent.RIGHT_BUTTON) {

				}
				if (Mouse.getEventButton() == StaticContent.LEFT_BUTTON) {

				}
				if (Mouse.getEventButton() == StaticContent.MIDDLE_BUTTON) {

				}
			}
		}
	}

	@Override
	public void update(int difTime) {
		super.update(difTime);

	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void renderInterface() {
		GL11.glPushMatrix();
		GL11.glLoadIdentity(); // Load the Identity Matrix to reset our drawing
								// locations

		HUD(currenteHUD);
		RenderGL.drawRectWithColor(new PVector(1f, 0f, 0f), width - width
				* 0.015f, height * 0.1f, width * 0.7f, height * 0.9f, 1f);

		for (int i = 0; i < selectedCards.length; i++) {
			selectedCards[i].render();
		}
		start.render();
		/* Ready button */
		GL11.glColor4f(1, 1, 1, 1);
		GL11.glPopMatrix();

	}

	public void btnTipos() {
		collector = new RectangleGL(TextureManager.btnCollector, width * 0.08f,
				height * 0.05f, width * 0.2f, height * 0.1f, 1);
		collector.render();
		// ====================================================================
		melee = new RectangleGL(TextureManager.btnMelee, width * 0.18f,
				height * 0.05f, width * 0.3f, height * 0.1f, 1);
		melee.render();
		// ====================================================================
		range = new RectangleGL(TextureManager.btnRange, width * 0.28f,
				height * 0.05f, width * 0.4f, height * 0.1f, 1);
		range.render();
		// ====================================================================
		area = new RectangleGL(TextureManager.btnArea, width * 0.38f,
				height * 0.05f, width * 0.5f, height * 0.1f, 1);
		area.render();
	}

	public void HUD(int HUDAtual) {

		switch (HUDAtual) {
		case HUDCOLLECTOR:
			fundo();
			btnTipos();
			cardsCollector();
			break;
		case HUDMELEE:
			fundo();
			btnTipos();
			cardsMelee();
			break;
		case HUDRANGE:
			fundo();
			btnTipos();
			cardsRange();
			break;
		case HUDAREA:
			fundo();
			btnTipos();
			cardsArea();

			break;

		default:
			break;
		}

	}

	public void fundo() {
		RenderGL.drawRectWithColor(new PVector(1f, 0.5f, 0f), width * 0.01f,
				height * 0.1f, width - (width * 0.01f), height
						- (height * 0.05f), 1);
	}

	public void cardsArea() {
		for (int i = 0; i < 3; i++) {
			RenderGL.drawRectWithColor(new PVector(0.5f, 0.8f, 0f), width
					* 0.03f + (width * 0.033f + width * 0.2f) * i,
					height * 0.45f, width * 0.03f
							+ ((width * 0.033f + width * 0.2f) * i) + width
							* 0.2f, height * 0.45f + (height * 0.4f), 1);
			cards[i] = new RectangleGL(TextureManager.cards[i + 9], offLeft
					+ (spacingX + widthCard) * i, offTop, offLeft
					+ ((spacingX + widthCard) * i) + widthCard, offTop
					+ (heightCard), 1);
			cards[i].render();

		}
	}

	public void cardsRange() {
		for (int i = 0; i < 3; i++) {
			RenderGL.drawRectWithColor(new PVector(0.5f, 0f, 1f), width * 0.03f
					+ (width * 0.033f + width * 0.2f) * i, height * 0.45f,
					width * 0.03f + ((width * 0.033f + width * 0.2f) * i)
							+ width * 0.2f, height * 0.45f + (height * 0.4f), 1);

			cards[i] = new RectangleGL(TextureManager.cards[i + 6], offLeft
					+ (spacingX + widthCard) * i, offTop, offLeft
					+ ((spacingX + widthCard) * i) + widthCard, offTop
					+ (heightCard), 1);
			cards[i].render();

		}
	}

	public void cardsMelee() {
		for (int i = 0; i < 3; i++) {
			RenderGL.drawRectWithColor(new PVector(0.5f, 0.8f, 1f), width
					* 0.03f + (width * 0.033f + width * 0.2f) * i,
					height * 0.45f, width * 0.03f
							+ ((width * 0.033f + width * 0.2f) * i) + width
							* 0.2f, height * 0.45f + (height * 0.4f), 1);
			cards[i] = new RectangleGL(TextureManager.cards[i + 3], offLeft
					+ (spacingX + widthCard) * i, offTop, offLeft
					+ ((spacingX + widthCard) * i) + widthCard, offTop
					+ (heightCard), 1);
			cards[i].render();

		}
	}

	public void cardsCollector() {
		for (int i = 0; i < 3; i++) {
			RenderGL.drawRectWithColor(new PVector(0.5f, 0.8f, 1f), width
					* 0.03f + (width * 0.033f + width * 0.2f) * i,
					height * 0.45f, width * 0.03f
							+ ((width * 0.033f + width * 0.2f) * i) + width
							* 0.2f, height * 0.45f + (height * 0.4f), 1);

			cards[i] = new RectangleGL(TextureManager.cards[i], offLeft
					+ (spacingX + widthCard) * i, offTop, offLeft
					+ ((spacingX + widthCard) * i) + widthCard, offTop
					+ (heightCard), 1);
			cards[i].render();

		}
	}
}
