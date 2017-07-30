package wuxc.single.railwayparty.layout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.branch.Statisticsforsex;

public class PartViewforsex extends View {

	private Paint xLinePaint;// 坐标轴 轴线 画笔：
	private Paint hLinePaint;// 坐标轴水平内部 虚线画笔
	private Paint titlePaint;// 绘制文本的画笔
	private Paint paint;// 矩形画笔 柱状图的样式信息
	private double[] progress;// 7 条
	private double[] aniProgress;// 实现动画的值
	private final int TRUE = 0;// 在柱状图上显示数字
	private int[] text;
	// 坐标轴左侧的数标
	private String[] ySteps;
	// 坐标轴底部的星期数
	private String[] xWeeks;

	private HistogramAnimation ani;

	public PartViewforsex(Context context) {
		super(context);
		init(context, null);
	}

	public PartViewforsex(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context, attrs);
	}

	private void init(Context context, AttributeSet attrs) {

		ySteps = new String[] { "100%", "75%", "50%", "25%", "0" };
		xWeeks = new String[] { "女", "男" };
		text = new int[] { 0, 0 };
		aniProgress = new double[] { 0, 0 };
		ani = new HistogramAnimation();
		ani.setDuration(100);

		xLinePaint = new Paint();
		hLinePaint = new Paint();
		titlePaint = new Paint();
		paint = new Paint();

		xLinePaint.setColor(Color.DKGRAY);
		hLinePaint.setColor(Color.LTGRAY);
		titlePaint.setColor(Color.BLACK);
	}

	public void setText(int[] text) {

		this.text = text;

		this.postInvalidate();// 可以子线程 更新视图的方法调用。
	}

	public void setProgress(double[] data) {
		this.progress = data;
		// this.invalidate(); //失效的意思。
		// this.postInvalidate(); // 可以子线程 更新视图的方法调用。
		this.startAnimation(ani);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return super.onTouchEvent(event);
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		int width = getWidth();
		int height = getHeight() - 200;

		// 1 绘制坐标线：startX, startY, stopX, stopY, paint
		int startX = dip2px(getContext(), 50);
		int startY = dip2px(getContext(), 10);
		int stopX = dip2px(getContext(), 50);
		int stopY = dip2px(getContext(), 320);
		canvas.drawLine(100, 10, 100, height, xLinePaint);

		canvas.drawLine(100, height, width - 10, height, xLinePaint);

		// 2 绘制坐标内部的水平线

		int leftHeight = height - 20;// 左侧外周的 需要划分的高度：

		int hPerHeight = leftHeight / 4;// 分成四部分

		hLinePaint.setTextAlign(Align.CENTER);
		for (int i = 0; i < 4; i++) {
			canvas.drawLine(100, 20 + i * hPerHeight, width - 10, 20 + i * hPerHeight, hLinePaint);
		}

		// 3 绘制 Y 周坐标

		titlePaint.setTextAlign(Align.RIGHT);
		titlePaint.setTextSize(25);
		titlePaint.setAntiAlias(true);
		titlePaint.setStyle(Paint.Style.FILL);
		for (int i = 0; i < ySteps.length; i++) {
			canvas.drawText(ySteps[i], 100, 30 + i * hPerHeight, titlePaint);
		}

		// 4 绘制 X 周 做坐标
		int xAxisLength = width - 30;
		int columCount = xWeeks.length + 1;
		int step = xAxisLength / columCount;
		int temp = step;
		for (int i = 0; i < columCount - 1; i++) {
			// text, baseX, baseY, textPaint
			canvas.drawText(xWeeks[i], 55 + temp * (i + 1), height + 60, titlePaint);
		}

		// 5 绘制矩形

		if (aniProgress != null && aniProgress.length > 0) {
			for (int i = 0; i < aniProgress.length; i++) {// 循环遍历将7条柱状图形画出来
				double value = aniProgress[i];
				paint.setAntiAlias(true);// 抗锯齿效果
				paint.setStyle(Paint.Style.FILL);
				paint.setTextSize(22);// 字体大小
				paint.setColor(Color.parseColor("#c40505"));// 字体颜色
				Rect rect = new Rect();// 柱状图的形状

				rect.left = 30 + step * (i + 1) - 40;
				rect.right = 30 + step * (i + 1) + 40;
				int rh = (int) (leftHeight - leftHeight * (value / Statisticsforsex.total));
				rect.top = rh + 20;
				rect.bottom = height;

				Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.column);

				canvas.drawBitmap(bitmap, null, rect, paint);

				if (this.text[i] == TRUE) {
					canvas.drawText(value + "", 30 + step * (i + 1) - 30, rh + 10, paint);
				}

			}
		}

	}

	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	/**
	 * 集成animation的一个动画类
	 * 
	 * @author 李垭超
	 *
	 */
	private class HistogramAnimation extends Animation {
		@Override
		protected void applyTransformation(float interpolatedTime, Transformation t) {
			super.applyTransformation(interpolatedTime, t);
			if (interpolatedTime < 1.0f) {
				for (int i = 0; i < aniProgress.length; i++) {
					aniProgress[i] = (int) (progress[i] * interpolatedTime);
				}
			} else {
				for (int i = 0; i < aniProgress.length; i++) {
					aniProgress[i] = progress[i];
				}
			}
			postInvalidate();
		}
	}

}