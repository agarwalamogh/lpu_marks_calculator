package android.support.p003v7.widget;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p003v7.widget.RecyclerView.LayoutManager;
import android.support.p003v7.widget.RecyclerView.OnFlingListener;
import android.support.p003v7.widget.RecyclerView.OnScrollListener;
import android.support.p003v7.widget.RecyclerView.SmoothScroller;
import android.support.p003v7.widget.RecyclerView.SmoothScroller.Action;
import android.support.p003v7.widget.RecyclerView.SmoothScroller.ScrollVectorProvider;
import android.support.p003v7.widget.RecyclerView.State;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;

/* renamed from: android.support.v7.widget.SnapHelper */
public abstract class SnapHelper extends OnFlingListener {
    static final float MILLISECONDS_PER_INCH = 100.0f;
    private Scroller mGravityScroller;
    RecyclerView mRecyclerView;
    private final OnScrollListener mScrollListener = new OnScrollListener() {
        boolean mScrolled = false;

        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == 0 && this.mScrolled) {
                this.mScrolled = false;
                SnapHelper.this.snapToTargetExistingView();
            }
        }

        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if (dx != 0 || dy != 0) {
                this.mScrolled = true;
            }
        }
    };

    @Nullable
    public abstract int[] calculateDistanceToFinalSnap(@NonNull LayoutManager layoutManager, @NonNull View view);

    @Nullable
    public abstract View findSnapView(LayoutManager layoutManager);

    public abstract int findTargetSnapPosition(LayoutManager layoutManager, int i, int i2);

    public boolean onFling(int velocityX, int velocityY) {
        LayoutManager layoutManager = this.mRecyclerView.getLayoutManager();
        if (layoutManager == null || this.mRecyclerView.getAdapter() == null) {
            return false;
        }
        int minFlingVelocity = this.mRecyclerView.getMinFlingVelocity();
        if ((Math.abs(velocityY) > minFlingVelocity || Math.abs(velocityX) > minFlingVelocity) && snapFromFling(layoutManager, velocityX, velocityY)) {
            return true;
        }
        return false;
    }

    public void attachToRecyclerView(@Nullable RecyclerView recyclerView) throws IllegalStateException {
        if (this.mRecyclerView != recyclerView) {
            if (this.mRecyclerView != null) {
                destroyCallbacks();
            }
            this.mRecyclerView = recyclerView;
            if (this.mRecyclerView != null) {
                setupCallbacks();
                this.mGravityScroller = new Scroller(this.mRecyclerView.getContext(), new DecelerateInterpolator());
                snapToTargetExistingView();
            }
        }
    }

    private void setupCallbacks() throws IllegalStateException {
        if (this.mRecyclerView.getOnFlingListener() != null) {
            throw new IllegalStateException("An instance of OnFlingListener already set.");
        }
        this.mRecyclerView.addOnScrollListener(this.mScrollListener);
        this.mRecyclerView.setOnFlingListener(this);
    }

    private void destroyCallbacks() {
        this.mRecyclerView.removeOnScrollListener(this.mScrollListener);
        this.mRecyclerView.setOnFlingListener(null);
    }

    public int[] calculateScrollDistance(int velocityX, int velocityY) {
        this.mGravityScroller.fling(0, 0, velocityX, velocityY, Integer.MIN_VALUE, ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, Integer.MIN_VALUE, ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
        return new int[]{this.mGravityScroller.getFinalX(), this.mGravityScroller.getFinalY()};
    }

    private boolean snapFromFling(@NonNull LayoutManager layoutManager, int velocityX, int velocityY) {
        if (!(layoutManager instanceof ScrollVectorProvider)) {
            return false;
        }
        SmoothScroller smoothScroller = createSnapScroller(layoutManager);
        if (smoothScroller == null) {
            return false;
        }
        int targetPosition = findTargetSnapPosition(layoutManager, velocityX, velocityY);
        if (targetPosition == -1) {
            return false;
        }
        smoothScroller.setTargetPosition(targetPosition);
        layoutManager.startSmoothScroll(smoothScroller);
        return true;
    }

    /* access modifiers changed from: 0000 */
    public void snapToTargetExistingView() {
        if (this.mRecyclerView != null) {
            LayoutManager layoutManager = this.mRecyclerView.getLayoutManager();
            if (layoutManager != null) {
                View snapView = findSnapView(layoutManager);
                if (snapView != null) {
                    int[] snapDistance = calculateDistanceToFinalSnap(layoutManager, snapView);
                    if (snapDistance[0] != 0 || snapDistance[1] != 0) {
                        this.mRecyclerView.smoothScrollBy(snapDistance[0], snapDistance[1]);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    @Nullable
    public LinearSmoothScroller createSnapScroller(LayoutManager layoutManager) {
        if (!(layoutManager instanceof ScrollVectorProvider)) {
            return null;
        }
        return new LinearSmoothScroller(this.mRecyclerView.getContext()) {
            /* access modifiers changed from: protected */
            public void onTargetFound(View targetView, State state, Action action) {
                int[] snapDistances = SnapHelper.this.calculateDistanceToFinalSnap(SnapHelper.this.mRecyclerView.getLayoutManager(), targetView);
                int dx = snapDistances[0];
                int dy = snapDistances[1];
                int time = calculateTimeForDeceleration(Math.max(Math.abs(dx), Math.abs(dy)));
                if (time > 0) {
                    action.update(dx, dy, time, this.mDecelerateInterpolator);
                }
            }

            /* access modifiers changed from: protected */
            public float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return SnapHelper.MILLISECONDS_PER_INCH / ((float) displayMetrics.densityDpi);
            }
        };
    }
}
