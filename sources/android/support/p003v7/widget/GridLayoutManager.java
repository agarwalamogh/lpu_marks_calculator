package android.support.p003v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.p000v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.p000v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat;
import android.support.p003v7.widget.RecyclerView.LayoutManager.LayoutPrefetchRegistry;
import android.support.p003v7.widget.RecyclerView.Recycler;
import android.support.p003v7.widget.RecyclerView.State;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import java.util.Arrays;

/* renamed from: android.support.v7.widget.GridLayoutManager */
public class GridLayoutManager extends LinearLayoutManager {
    private static final boolean DEBUG = false;
    public static final int DEFAULT_SPAN_COUNT = -1;
    private static final String TAG = "GridLayoutManager";
    int[] mCachedBorders;
    final Rect mDecorInsets = new Rect();
    boolean mPendingSpanCountChange = false;
    final SparseIntArray mPreLayoutSpanIndexCache = new SparseIntArray();
    final SparseIntArray mPreLayoutSpanSizeCache = new SparseIntArray();
    View[] mSet;
    int mSpanCount = -1;
    SpanSizeLookup mSpanSizeLookup = new DefaultSpanSizeLookup();

    /* renamed from: android.support.v7.widget.GridLayoutManager$DefaultSpanSizeLookup */
    public static final class DefaultSpanSizeLookup extends SpanSizeLookup {
        public int getSpanSize(int position) {
            return 1;
        }

        public int getSpanIndex(int position, int spanCount) {
            return position % spanCount;
        }
    }

    /* renamed from: android.support.v7.widget.GridLayoutManager$LayoutParams */
    public static class LayoutParams extends android.support.p003v7.widget.RecyclerView.LayoutParams {
        public static final int INVALID_SPAN_ID = -1;
        int mSpanIndex = -1;
        int mSpanSize = 0;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams source) {
            super(source);
        }

        public LayoutParams(android.support.p003v7.widget.RecyclerView.LayoutParams source) {
            super(source);
        }

        public int getSpanIndex() {
            return this.mSpanIndex;
        }

        public int getSpanSize() {
            return this.mSpanSize;
        }
    }

    /* renamed from: android.support.v7.widget.GridLayoutManager$SpanSizeLookup */
    public static abstract class SpanSizeLookup {
        private boolean mCacheSpanIndices = false;
        final SparseIntArray mSpanIndexCache = new SparseIntArray();

        public abstract int getSpanSize(int i);

        public void setSpanIndexCacheEnabled(boolean cacheSpanIndices) {
            this.mCacheSpanIndices = cacheSpanIndices;
        }

        public void invalidateSpanIndexCache() {
            this.mSpanIndexCache.clear();
        }

        public boolean isSpanIndexCacheEnabled() {
            return this.mCacheSpanIndices;
        }

        /* access modifiers changed from: 0000 */
        public int getCachedSpanIndex(int position, int spanCount) {
            if (!this.mCacheSpanIndices) {
                return getSpanIndex(position, spanCount);
            }
            int existing = this.mSpanIndexCache.get(position, -1);
            if (existing != -1) {
                return existing;
            }
            int value = getSpanIndex(position, spanCount);
            this.mSpanIndexCache.put(position, value);
            return value;
        }

        public int getSpanIndex(int position, int spanCount) {
            int span;
            int positionSpanSize = getSpanSize(position);
            if (positionSpanSize == spanCount) {
                return 0;
            }
            int span2 = 0;
            int startPos = 0;
            if (this.mCacheSpanIndices && this.mSpanIndexCache.size() > 0) {
                int prevKey = findReferenceIndexFromCache(position);
                if (prevKey >= 0) {
                    span2 = this.mSpanIndexCache.get(prevKey) + getSpanSize(prevKey);
                    startPos = prevKey + 1;
                }
            }
            for (int i = startPos; i < position; i++) {
                int size = getSpanSize(i);
                span += size;
                if (span == spanCount) {
                    span = 0;
                } else if (span > spanCount) {
                    span = size;
                }
            }
            if (span + positionSpanSize > spanCount) {
                return 0;
            }
            return span;
        }

        /* access modifiers changed from: 0000 */
        public int findReferenceIndexFromCache(int position) {
            int lo = 0;
            int hi = this.mSpanIndexCache.size() - 1;
            while (lo <= hi) {
                int mid = (lo + hi) >>> 1;
                if (this.mSpanIndexCache.keyAt(mid) < position) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
            int index = lo - 1;
            if (index < 0 || index >= this.mSpanIndexCache.size()) {
                return -1;
            }
            return this.mSpanIndexCache.keyAt(index);
        }

        public int getSpanGroupIndex(int adapterPosition, int spanCount) {
            int span = 0;
            int group = 0;
            int positionSpanSize = getSpanSize(adapterPosition);
            for (int i = 0; i < adapterPosition; i++) {
                int size = getSpanSize(i);
                span += size;
                if (span == spanCount) {
                    span = 0;
                    group++;
                } else if (span > spanCount) {
                    span = size;
                    group++;
                }
            }
            if (span + positionSpanSize > spanCount) {
                return group + 1;
            }
            return group;
        }
    }

    public GridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setSpanCount(getProperties(context, attrs, defStyleAttr, defStyleRes).spanCount);
    }

    public GridLayoutManager(Context context, int spanCount) {
        super(context);
        setSpanCount(spanCount);
    }

    public GridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
        setSpanCount(spanCount);
    }

    public void setStackFromEnd(boolean stackFromEnd) {
        if (stackFromEnd) {
            throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
        }
        super.setStackFromEnd(false);
    }

    public int getRowCountForAccessibility(Recycler recycler, State state) {
        if (this.mOrientation == 0) {
            return this.mSpanCount;
        }
        if (state.getItemCount() < 1) {
            return 0;
        }
        return getSpanGroupIndex(recycler, state, state.getItemCount() - 1) + 1;
    }

    public int getColumnCountForAccessibility(Recycler recycler, State state) {
        if (this.mOrientation == 1) {
            return this.mSpanCount;
        }
        if (state.getItemCount() < 1) {
            return 0;
        }
        return getSpanGroupIndex(recycler, state, state.getItemCount() - 1) + 1;
    }

    public void onInitializeAccessibilityNodeInfoForItem(Recycler recycler, State state, View host, AccessibilityNodeInfoCompat info) {
        android.view.ViewGroup.LayoutParams lp = host.getLayoutParams();
        if (!(lp instanceof LayoutParams)) {
            super.onInitializeAccessibilityNodeInfoForItem(host, info);
            return;
        }
        LayoutParams glp = (LayoutParams) lp;
        int spanGroupIndex = getSpanGroupIndex(recycler, state, glp.getViewLayoutPosition());
        if (this.mOrientation == 0) {
            info.setCollectionItemInfo(CollectionItemInfoCompat.obtain(glp.getSpanIndex(), glp.getSpanSize(), spanGroupIndex, 1, this.mSpanCount > 1 && glp.getSpanSize() == this.mSpanCount, false));
        } else {
            info.setCollectionItemInfo(CollectionItemInfoCompat.obtain(spanGroupIndex, 1, glp.getSpanIndex(), glp.getSpanSize(), this.mSpanCount > 1 && glp.getSpanSize() == this.mSpanCount, false));
        }
    }

    public void onLayoutChildren(Recycler recycler, State state) {
        if (state.isPreLayout()) {
            cachePreLayoutSpanMapping();
        }
        super.onLayoutChildren(recycler, state);
        clearPreLayoutSpanMappingCache();
    }

    public void onLayoutCompleted(State state) {
        super.onLayoutCompleted(state);
        this.mPendingSpanCountChange = false;
    }

    private void clearPreLayoutSpanMappingCache() {
        this.mPreLayoutSpanSizeCache.clear();
        this.mPreLayoutSpanIndexCache.clear();
    }

    private void cachePreLayoutSpanMapping() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            LayoutParams lp = (LayoutParams) getChildAt(i).getLayoutParams();
            int viewPosition = lp.getViewLayoutPosition();
            this.mPreLayoutSpanSizeCache.put(viewPosition, lp.getSpanSize());
            this.mPreLayoutSpanIndexCache.put(viewPosition, lp.getSpanIndex());
        }
    }

    public void onItemsAdded(RecyclerView recyclerView, int positionStart, int itemCount) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }

    public void onItemsChanged(RecyclerView recyclerView) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }

    public void onItemsRemoved(RecyclerView recyclerView, int positionStart, int itemCount) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }

    public void onItemsUpdated(RecyclerView recyclerView, int positionStart, int itemCount, Object payload) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }

    public void onItemsMoved(RecyclerView recyclerView, int from, int to, int itemCount) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }

    public android.support.p003v7.widget.RecyclerView.LayoutParams generateDefaultLayoutParams() {
        if (this.mOrientation == 0) {
            return new LayoutParams(-2, -1);
        }
        return new LayoutParams(-1, -2);
    }

    public android.support.p003v7.widget.RecyclerView.LayoutParams generateLayoutParams(Context c, AttributeSet attrs) {
        return new LayoutParams(c, attrs);
    }

    public android.support.p003v7.widget.RecyclerView.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams lp) {
        if (lp instanceof MarginLayoutParams) {
            return new LayoutParams((MarginLayoutParams) lp);
        }
        return new LayoutParams(lp);
    }

    public boolean checkLayoutParams(android.support.p003v7.widget.RecyclerView.LayoutParams lp) {
        return lp instanceof LayoutParams;
    }

    public void setSpanSizeLookup(SpanSizeLookup spanSizeLookup) {
        this.mSpanSizeLookup = spanSizeLookup;
    }

    public SpanSizeLookup getSpanSizeLookup() {
        return this.mSpanSizeLookup;
    }

    private void updateMeasurements() {
        int totalSpace;
        if (getOrientation() == 1) {
            totalSpace = (getWidth() - getPaddingRight()) - getPaddingLeft();
        } else {
            totalSpace = (getHeight() - getPaddingBottom()) - getPaddingTop();
        }
        calculateItemBorders(totalSpace);
    }

    public void setMeasuredDimension(Rect childrenBounds, int wSpec, int hSpec) {
        int width;
        int height;
        if (this.mCachedBorders == null) {
            super.setMeasuredDimension(childrenBounds, wSpec, hSpec);
        }
        int horizontalPadding = getPaddingLeft() + getPaddingRight();
        int verticalPadding = getPaddingTop() + getPaddingBottom();
        if (this.mOrientation == 1) {
            height = chooseSize(hSpec, childrenBounds.height() + verticalPadding, getMinimumHeight());
            width = chooseSize(wSpec, this.mCachedBorders[this.mCachedBorders.length - 1] + horizontalPadding, getMinimumWidth());
        } else {
            width = chooseSize(wSpec, childrenBounds.width() + horizontalPadding, getMinimumWidth());
            height = chooseSize(hSpec, this.mCachedBorders[this.mCachedBorders.length - 1] + verticalPadding, getMinimumHeight());
        }
        setMeasuredDimension(width, height);
    }

    private void calculateItemBorders(int totalSpace) {
        this.mCachedBorders = calculateItemBorders(this.mCachedBorders, this.mSpanCount, totalSpace);
    }

    static int[] calculateItemBorders(int[] cachedBorders, int spanCount, int totalSpace) {
        if (!(cachedBorders != null && cachedBorders.length == spanCount + 1 && cachedBorders[cachedBorders.length - 1] == totalSpace)) {
            cachedBorders = new int[(spanCount + 1)];
        }
        cachedBorders[0] = 0;
        int sizePerSpan = totalSpace / spanCount;
        int sizePerSpanRemainder = totalSpace % spanCount;
        int consumedPixels = 0;
        int additionalSize = 0;
        for (int i = 1; i <= spanCount; i++) {
            int itemSize = sizePerSpan;
            additionalSize += sizePerSpanRemainder;
            if (additionalSize > 0 && spanCount - additionalSize < sizePerSpanRemainder) {
                itemSize++;
                additionalSize -= spanCount;
            }
            consumedPixels += itemSize;
            cachedBorders[i] = consumedPixels;
        }
        return cachedBorders;
    }

    /* access modifiers changed from: 0000 */
    public int getSpaceForSpanRange(int startSpan, int spanSize) {
        if (this.mOrientation != 1 || !isLayoutRTL()) {
            return this.mCachedBorders[startSpan + spanSize] - this.mCachedBorders[startSpan];
        }
        return this.mCachedBorders[this.mSpanCount - startSpan] - this.mCachedBorders[(this.mSpanCount - startSpan) - spanSize];
    }

    /* access modifiers changed from: 0000 */
    public void onAnchorReady(Recycler recycler, State state, AnchorInfo anchorInfo, int itemDirection) {
        super.onAnchorReady(recycler, state, anchorInfo, itemDirection);
        updateMeasurements();
        if (state.getItemCount() > 0 && !state.isPreLayout()) {
            ensureAnchorIsInCorrectSpan(recycler, state, anchorInfo, itemDirection);
        }
        ensureViewSet();
    }

    private void ensureViewSet() {
        if (this.mSet == null || this.mSet.length != this.mSpanCount) {
            this.mSet = new View[this.mSpanCount];
        }
    }

    public int scrollHorizontallyBy(int dx, Recycler recycler, State state) {
        updateMeasurements();
        ensureViewSet();
        return super.scrollHorizontallyBy(dx, recycler, state);
    }

    public int scrollVerticallyBy(int dy, Recycler recycler, State state) {
        updateMeasurements();
        ensureViewSet();
        return super.scrollVerticallyBy(dy, recycler, state);
    }

    private void ensureAnchorIsInCorrectSpan(Recycler recycler, State state, AnchorInfo anchorInfo, int itemDirection) {
        boolean layingOutInPrimaryDirection = true;
        if (itemDirection != 1) {
            layingOutInPrimaryDirection = false;
        }
        int span = getSpanIndex(recycler, state, anchorInfo.mPosition);
        if (layingOutInPrimaryDirection) {
            while (span > 0 && anchorInfo.mPosition > 0) {
                anchorInfo.mPosition--;
                span = getSpanIndex(recycler, state, anchorInfo.mPosition);
            }
            return;
        }
        int indexLimit = state.getItemCount() - 1;
        int pos = anchorInfo.mPosition;
        int bestSpan = span;
        while (pos < indexLimit) {
            int next = getSpanIndex(recycler, state, pos + 1);
            if (next <= bestSpan) {
                break;
            }
            pos++;
            bestSpan = next;
        }
        anchorInfo.mPosition = pos;
    }

    /* access modifiers changed from: 0000 */
    public View findReferenceChild(Recycler recycler, State state, int start, int end, int itemCount) {
        ensureLayoutState();
        View invalidMatch = null;
        View outOfBoundsMatch = null;
        int boundsStart = this.mOrientationHelper.getStartAfterPadding();
        int boundsEnd = this.mOrientationHelper.getEndAfterPadding();
        int diff = end > start ? 1 : -1;
        for (int i = start; i != end; i += diff) {
            View view = getChildAt(i);
            int position = getPosition(view);
            if (position >= 0 && position < itemCount && getSpanIndex(recycler, state, position) == 0) {
                if (((android.support.p003v7.widget.RecyclerView.LayoutParams) view.getLayoutParams()).isItemRemoved()) {
                    if (invalidMatch == null) {
                        invalidMatch = view;
                    }
                } else if (this.mOrientationHelper.getDecoratedStart(view) < boundsEnd && this.mOrientationHelper.getDecoratedEnd(view) >= boundsStart) {
                    return view;
                } else {
                    if (outOfBoundsMatch == null) {
                        outOfBoundsMatch = view;
                    }
                }
            }
        }
        if (outOfBoundsMatch == null) {
            outOfBoundsMatch = invalidMatch;
        }
        return outOfBoundsMatch;
    }

    private int getSpanGroupIndex(Recycler recycler, State state, int viewPosition) {
        if (!state.isPreLayout()) {
            return this.mSpanSizeLookup.getSpanGroupIndex(viewPosition, this.mSpanCount);
        }
        int adapterPosition = recycler.convertPreLayoutPositionToPostLayout(viewPosition);
        if (adapterPosition != -1) {
            return this.mSpanSizeLookup.getSpanGroupIndex(adapterPosition, this.mSpanCount);
        }
        Log.w(TAG, "Cannot find span size for pre layout position. " + viewPosition);
        return 0;
    }

    private int getSpanIndex(Recycler recycler, State state, int pos) {
        if (!state.isPreLayout()) {
            return this.mSpanSizeLookup.getCachedSpanIndex(pos, this.mSpanCount);
        }
        int cached = this.mPreLayoutSpanIndexCache.get(pos, -1);
        if (cached != -1) {
            return cached;
        }
        int adapterPosition = recycler.convertPreLayoutPositionToPostLayout(pos);
        if (adapterPosition != -1) {
            return this.mSpanSizeLookup.getCachedSpanIndex(adapterPosition, this.mSpanCount);
        }
        Log.w(TAG, "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + pos);
        return 0;
    }

    private int getSpanSize(Recycler recycler, State state, int pos) {
        if (!state.isPreLayout()) {
            return this.mSpanSizeLookup.getSpanSize(pos);
        }
        int cached = this.mPreLayoutSpanSizeCache.get(pos, -1);
        if (cached != -1) {
            return cached;
        }
        int adapterPosition = recycler.convertPreLayoutPositionToPostLayout(pos);
        if (adapterPosition != -1) {
            return this.mSpanSizeLookup.getSpanSize(adapterPosition);
        }
        Log.w(TAG, "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + pos);
        return 1;
    }

    /* access modifiers changed from: 0000 */
    public void collectPrefetchPositionsForLayoutState(State state, LayoutState layoutState, LayoutPrefetchRegistry layoutPrefetchRegistry) {
        int remainingSpan = this.mSpanCount;
        for (int count = 0; count < this.mSpanCount && layoutState.hasMore(state) && remainingSpan > 0; count++) {
            int pos = layoutState.mCurrentPosition;
            layoutPrefetchRegistry.addPosition(pos, layoutState.mScrollingOffset);
            remainingSpan -= this.mSpanSizeLookup.getSpanSize(pos);
            layoutState.mCurrentPosition += layoutState.mItemDirection;
        }
    }

    /* access modifiers changed from: 0000 */
    public void layoutChunk(Recycler recycler, State state, LayoutState layoutState, LayoutChunkResult result) {
        int wSpec;
        int hSpec;
        int otherDirSpecMode = this.mOrientationHelper.getModeInOther();
        boolean flexibleInOtherDir = otherDirSpecMode != 1073741824;
        int currentOtherDirSize = getChildCount() > 0 ? this.mCachedBorders[this.mSpanCount] : 0;
        if (flexibleInOtherDir) {
            updateMeasurements();
        }
        boolean layingOutInPrimaryDirection = layoutState.mItemDirection == 1;
        int count = 0;
        int consumedSpanCount = 0;
        int remainingSpan = this.mSpanCount;
        if (!layingOutInPrimaryDirection) {
            remainingSpan = getSpanIndex(recycler, state, layoutState.mCurrentPosition) + getSpanSize(recycler, state, layoutState.mCurrentPosition);
        }
        while (count < this.mSpanCount && layoutState.hasMore(state) && remainingSpan > 0) {
            int pos = layoutState.mCurrentPosition;
            int spanSize = getSpanSize(recycler, state, pos);
            if (spanSize <= this.mSpanCount) {
                remainingSpan -= spanSize;
                if (remainingSpan >= 0) {
                    View view = layoutState.next(recycler);
                    if (view == null) {
                        break;
                    }
                    consumedSpanCount += spanSize;
                    this.mSet[count] = view;
                    count++;
                } else {
                    break;
                }
            } else {
                throw new IllegalArgumentException("Item at position " + pos + " requires " + spanSize + " spans but GridLayoutManager has only " + this.mSpanCount + " spans.");
            }
        }
        if (count == 0) {
            result.mFinished = true;
            return;
        }
        int maxSize = 0;
        float maxSizeInOther = 0.0f;
        assignSpans(recycler, state, count, consumedSpanCount, layingOutInPrimaryDirection);
        for (int i = 0; i < count; i++) {
            View view2 = this.mSet[i];
            if (layoutState.mScrapList == null) {
                if (layingOutInPrimaryDirection) {
                    addView(view2);
                } else {
                    addView(view2, 0);
                }
            } else if (layingOutInPrimaryDirection) {
                addDisappearingView(view2);
            } else {
                addDisappearingView(view2, 0);
            }
            calculateItemDecorationsForChild(view2, this.mDecorInsets);
            measureChild(view2, otherDirSpecMode, false);
            int size = this.mOrientationHelper.getDecoratedMeasurement(view2);
            if (size > maxSize) {
                maxSize = size;
            }
            float otherSize = (1.0f * ((float) this.mOrientationHelper.getDecoratedMeasurementInOther(view2))) / ((float) ((LayoutParams) view2.getLayoutParams()).mSpanSize);
            if (otherSize > maxSizeInOther) {
                maxSizeInOther = otherSize;
            }
        }
        if (flexibleInOtherDir) {
            guessMeasurement(maxSizeInOther, currentOtherDirSize);
            maxSize = 0;
            for (int i2 = 0; i2 < count; i2++) {
                View view3 = this.mSet[i2];
                measureChild(view3, 1073741824, true);
                int size2 = this.mOrientationHelper.getDecoratedMeasurement(view3);
                if (size2 > maxSize) {
                    maxSize = size2;
                }
            }
        }
        for (int i3 = 0; i3 < count; i3++) {
            View view4 = this.mSet[i3];
            if (this.mOrientationHelper.getDecoratedMeasurement(view4) != maxSize) {
                LayoutParams lp = (LayoutParams) view4.getLayoutParams();
                Rect decorInsets = lp.mDecorInsets;
                int verticalInsets = decorInsets.top + decorInsets.bottom + lp.topMargin + lp.bottomMargin;
                int horizontalInsets = decorInsets.left + decorInsets.right + lp.leftMargin + lp.rightMargin;
                int totalSpaceInOther = getSpaceForSpanRange(lp.mSpanIndex, lp.mSpanSize);
                if (this.mOrientation == 1) {
                    wSpec = getChildMeasureSpec(totalSpaceInOther, 1073741824, horizontalInsets, lp.width, false);
                    hSpec = MeasureSpec.makeMeasureSpec(maxSize - verticalInsets, 1073741824);
                } else {
                    wSpec = MeasureSpec.makeMeasureSpec(maxSize - horizontalInsets, 1073741824);
                    hSpec = getChildMeasureSpec(totalSpaceInOther, 1073741824, verticalInsets, lp.height, false);
                }
                measureChildWithDecorationsAndMargin(view4, wSpec, hSpec, true);
            }
        }
        result.mConsumed = maxSize;
        int left = 0;
        int right = 0;
        int top = 0;
        int bottom = 0;
        if (this.mOrientation == 1) {
            if (layoutState.mLayoutDirection == -1) {
                bottom = layoutState.mOffset;
                top = bottom - maxSize;
            } else {
                top = layoutState.mOffset;
                bottom = top + maxSize;
            }
        } else if (layoutState.mLayoutDirection == -1) {
            right = layoutState.mOffset;
            left = right - maxSize;
        } else {
            left = layoutState.mOffset;
            right = left + maxSize;
        }
        for (int i4 = 0; i4 < count; i4++) {
            View view5 = this.mSet[i4];
            LayoutParams params = (LayoutParams) view5.getLayoutParams();
            if (this.mOrientation != 1) {
                top = getPaddingTop() + this.mCachedBorders[params.mSpanIndex];
                bottom = top + this.mOrientationHelper.getDecoratedMeasurementInOther(view5);
            } else if (isLayoutRTL()) {
                right = getPaddingLeft() + this.mCachedBorders[this.mSpanCount - params.mSpanIndex];
                left = right - this.mOrientationHelper.getDecoratedMeasurementInOther(view5);
            } else {
                left = getPaddingLeft() + this.mCachedBorders[params.mSpanIndex];
                right = left + this.mOrientationHelper.getDecoratedMeasurementInOther(view5);
            }
            layoutDecoratedWithMargins(view5, left, top, right, bottom);
            if (params.isItemRemoved() || params.isItemChanged()) {
                result.mIgnoreConsumed = true;
            }
            result.mFocusable |= view5.isFocusable();
        }
        Arrays.fill(this.mSet, null);
    }

    private void measureChild(View view, int otherDirParentSpecMode, boolean alreadyMeasured) {
        int hSpec;
        int wSpec;
        LayoutParams lp = (LayoutParams) view.getLayoutParams();
        Rect decorInsets = lp.mDecorInsets;
        int verticalInsets = decorInsets.top + decorInsets.bottom + lp.topMargin + lp.bottomMargin;
        int horizontalInsets = decorInsets.left + decorInsets.right + lp.leftMargin + lp.rightMargin;
        int availableSpaceInOther = getSpaceForSpanRange(lp.mSpanIndex, lp.mSpanSize);
        if (this.mOrientation == 1) {
            wSpec = getChildMeasureSpec(availableSpaceInOther, otherDirParentSpecMode, horizontalInsets, lp.width, false);
            hSpec = getChildMeasureSpec(this.mOrientationHelper.getTotalSpace(), getHeightMode(), verticalInsets, lp.height, true);
        } else {
            hSpec = getChildMeasureSpec(availableSpaceInOther, otherDirParentSpecMode, verticalInsets, lp.height, false);
            wSpec = getChildMeasureSpec(this.mOrientationHelper.getTotalSpace(), getWidthMode(), horizontalInsets, lp.width, true);
        }
        measureChildWithDecorationsAndMargin(view, wSpec, hSpec, alreadyMeasured);
    }

    private void guessMeasurement(float maxSizeInOther, int currentOtherDirSize) {
        calculateItemBorders(Math.max(Math.round(((float) this.mSpanCount) * maxSizeInOther), currentOtherDirSize));
    }

    private void measureChildWithDecorationsAndMargin(View child, int widthSpec, int heightSpec, boolean alreadyMeasured) {
        boolean measure;
        android.support.p003v7.widget.RecyclerView.LayoutParams lp = (android.support.p003v7.widget.RecyclerView.LayoutParams) child.getLayoutParams();
        if (alreadyMeasured) {
            measure = shouldReMeasureChild(child, widthSpec, heightSpec, lp);
        } else {
            measure = shouldMeasureChild(child, widthSpec, heightSpec, lp);
        }
        if (measure) {
            child.measure(widthSpec, heightSpec);
        }
    }

    private void assignSpans(Recycler recycler, State state, int count, int consumedSpanCount, boolean layingOutInPrimaryDirection) {
        int start;
        int end;
        int diff;
        if (layingOutInPrimaryDirection) {
            start = 0;
            end = count;
            diff = 1;
        } else {
            start = count - 1;
            end = -1;
            diff = -1;
        }
        int span = 0;
        for (int i = start; i != end; i += diff) {
            View view = this.mSet[i];
            LayoutParams params = (LayoutParams) view.getLayoutParams();
            params.mSpanSize = getSpanSize(recycler, state, getPosition(view));
            params.mSpanIndex = span;
            span += params.mSpanSize;
        }
    }

    public int getSpanCount() {
        return this.mSpanCount;
    }

    public void setSpanCount(int spanCount) {
        if (spanCount != this.mSpanCount) {
            this.mPendingSpanCountChange = true;
            if (spanCount < 1) {
                throw new IllegalArgumentException("Span count should be at least 1. Provided " + spanCount);
            }
            this.mSpanCount = spanCount;
            this.mSpanSizeLookup.invalidateSpanIndexCache();
            requestLayout();
        }
    }

    public View onFocusSearchFailed(View focused, int focusDirection, Recycler recycler, State state) {
        int start;
        int inc;
        int limit;
        View prevFocusedChild = findContainingItemView(focused);
        if (prevFocusedChild == null) {
            return null;
        }
        LayoutParams lp = (LayoutParams) prevFocusedChild.getLayoutParams();
        int prevSpanStart = lp.mSpanIndex;
        int prevSpanEnd = lp.mSpanIndex + lp.mSpanSize;
        if (super.onFocusSearchFailed(focused, focusDirection, recycler, state) == null) {
            return null;
        }
        if ((convertFocusDirectionToLayoutDirection(focusDirection) == 1) != this.mShouldReverseLayout) {
            start = getChildCount() - 1;
            inc = -1;
            limit = -1;
        } else {
            start = 0;
            inc = 1;
            limit = getChildCount();
        }
        boolean preferLastSpan = this.mOrientation == 1 && isLayoutRTL();
        View weakCandidate = null;
        int weakCandidateSpanIndex = -1;
        int weakCandidateOverlap = 0;
        for (int i = start; i != limit; i += inc) {
            View candidate = getChildAt(i);
            if (candidate == prevFocusedChild) {
                break;
            }
            if (candidate.isFocusable()) {
                LayoutParams candidateLp = (LayoutParams) candidate.getLayoutParams();
                int candidateStart = candidateLp.mSpanIndex;
                int candidateEnd = candidateLp.mSpanIndex + candidateLp.mSpanSize;
                if (candidateStart == prevSpanStart && candidateEnd == prevSpanEnd) {
                    return candidate;
                }
                boolean assignAsWeek = false;
                if (weakCandidate == null) {
                    assignAsWeek = true;
                } else {
                    int overlap = Math.min(candidateEnd, prevSpanEnd) - Math.max(candidateStart, prevSpanStart);
                    if (overlap > weakCandidateOverlap) {
                        assignAsWeek = true;
                    } else if (overlap == weakCandidateOverlap) {
                        if (preferLastSpan == (candidateStart > weakCandidateSpanIndex)) {
                            assignAsWeek = true;
                        }
                    }
                }
                if (assignAsWeek) {
                    weakCandidate = candidate;
                    weakCandidateSpanIndex = candidateLp.mSpanIndex;
                    weakCandidateOverlap = Math.min(candidateEnd, prevSpanEnd) - Math.max(candidateStart, prevSpanStart);
                }
            }
        }
        return weakCandidate;
    }

    public boolean supportsPredictiveItemAnimations() {
        return this.mPendingSavedState == null && !this.mPendingSpanCountChange;
    }
}
