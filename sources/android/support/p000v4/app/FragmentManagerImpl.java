package android.support.p000v4.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.support.annotation.CallSuper;
import android.support.p000v4.app.Fragment.SavedState;
import android.support.p000v4.app.FragmentManager.BackStackEntry;
import android.support.p000v4.app.FragmentManager.FragmentLifecycleCallbacks;
import android.support.p000v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.p000v4.p002os.BuildCompat;
import android.support.p000v4.util.ArraySet;
import android.support.p000v4.util.DebugUtils;
import android.support.p000v4.util.LogWriter;
import android.support.p000v4.util.Pair;
import android.support.p000v4.view.LayoutInflaterFactory;
import android.support.p000v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* renamed from: android.support.v4.app.FragmentManagerImpl */
/* compiled from: FragmentManager */
final class FragmentManagerImpl extends FragmentManager implements LayoutInflaterFactory {
    static final Interpolator ACCELERATE_CUBIC = new AccelerateInterpolator(1.5f);
    static final Interpolator ACCELERATE_QUINT = new AccelerateInterpolator(2.5f);
    static final int ANIM_DUR = 220;
    public static final int ANIM_STYLE_CLOSE_ENTER = 3;
    public static final int ANIM_STYLE_CLOSE_EXIT = 4;
    public static final int ANIM_STYLE_FADE_ENTER = 5;
    public static final int ANIM_STYLE_FADE_EXIT = 6;
    public static final int ANIM_STYLE_OPEN_ENTER = 1;
    public static final int ANIM_STYLE_OPEN_EXIT = 2;
    static boolean DEBUG = false;
    static final Interpolator DECELERATE_CUBIC = new DecelerateInterpolator(1.5f);
    static final Interpolator DECELERATE_QUINT = new DecelerateInterpolator(2.5f);
    static final boolean HONEYCOMB;
    static final String TAG = "FragmentManager";
    static final String TARGET_REQUEST_CODE_STATE_TAG = "android:target_req_state";
    static final String TARGET_STATE_TAG = "android:target_state";
    static final String USER_VISIBLE_HINT_TAG = "android:user_visible_hint";
    static final String VIEW_STATE_TAG = "android:view_state";
    static Field sAnimationListenerField = null;
    ArrayList<Fragment> mActive;
    ArrayList<Fragment> mAdded;
    ArrayList<Integer> mAvailBackStackIndices;
    ArrayList<Integer> mAvailIndices;
    ArrayList<BackStackRecord> mBackStack;
    ArrayList<OnBackStackChangedListener> mBackStackChangeListeners;
    ArrayList<BackStackRecord> mBackStackIndices;
    FragmentContainer mContainer;
    ArrayList<Fragment> mCreatedMenus;
    int mCurState = 0;
    boolean mDestroyed;
    Runnable mExecCommit = new Runnable() {
        public void run() {
            FragmentManagerImpl.this.execPendingActions();
        }
    };
    boolean mExecutingActions;
    boolean mHavePendingDeferredStart;
    FragmentHostCallback mHost;
    private CopyOnWriteArrayList<Pair<FragmentLifecycleCallbacks, Boolean>> mLifecycleCallbacks;
    boolean mNeedMenuInvalidate;
    String mNoTransactionsBecause;
    Fragment mParent;
    ArrayList<OpGenerator> mPendingActions;
    ArrayList<StartEnterTransitionListener> mPostponedTransactions;
    SparseArray<Parcelable> mStateArray = null;
    Bundle mStateBundle = null;
    boolean mStateSaved;
    Runnable[] mTmpActions;
    ArrayList<Fragment> mTmpAddedFragments;
    ArrayList<Boolean> mTmpIsPop;
    ArrayList<BackStackRecord> mTmpRecords;

    /* renamed from: android.support.v4.app.FragmentManagerImpl$AnimateOnHWLayerIfNeededListener */
    /* compiled from: FragmentManager */
    static class AnimateOnHWLayerIfNeededListener implements AnimationListener {
        private AnimationListener mOriginalListener;
        private boolean mShouldRunOnHWLayer;
        View mView;

        public AnimateOnHWLayerIfNeededListener(View v, Animation anim) {
            if (v != null && anim != null) {
                this.mView = v;
            }
        }

        public AnimateOnHWLayerIfNeededListener(View v, Animation anim, AnimationListener listener) {
            if (v != null && anim != null) {
                this.mOriginalListener = listener;
                this.mView = v;
                this.mShouldRunOnHWLayer = true;
            }
        }

        @CallSuper
        public void onAnimationStart(Animation animation) {
            if (this.mOriginalListener != null) {
                this.mOriginalListener.onAnimationStart(animation);
            }
        }

        @CallSuper
        public void onAnimationEnd(Animation animation) {
            if (this.mView != null && this.mShouldRunOnHWLayer) {
                if (ViewCompat.isAttachedToWindow(this.mView) || BuildCompat.isAtLeastN()) {
                    this.mView.post(new Runnable() {
                        public void run() {
                            ViewCompat.setLayerType(AnimateOnHWLayerIfNeededListener.this.mView, 0, null);
                        }
                    });
                } else {
                    ViewCompat.setLayerType(this.mView, 0, null);
                }
            }
            if (this.mOriginalListener != null) {
                this.mOriginalListener.onAnimationEnd(animation);
            }
        }

        public void onAnimationRepeat(Animation animation) {
            if (this.mOriginalListener != null) {
                this.mOriginalListener.onAnimationRepeat(animation);
            }
        }
    }

    /* renamed from: android.support.v4.app.FragmentManagerImpl$FragmentTag */
    /* compiled from: FragmentManager */
    static class FragmentTag {
        public static final int[] Fragment = {16842755, 16842960, 16842961};
        public static final int Fragment_id = 1;
        public static final int Fragment_name = 0;
        public static final int Fragment_tag = 2;

        FragmentTag() {
        }
    }

    /* renamed from: android.support.v4.app.FragmentManagerImpl$OpGenerator */
    /* compiled from: FragmentManager */
    interface OpGenerator {
        boolean generateOps(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2);
    }

    /* renamed from: android.support.v4.app.FragmentManagerImpl$PopBackStackState */
    /* compiled from: FragmentManager */
    private class PopBackStackState implements OpGenerator {
        final int mFlags;
        final int mId;
        final String mName;

        PopBackStackState(String name, int id, int flags) {
            this.mName = name;
            this.mId = id;
            this.mFlags = flags;
        }

        public boolean generateOps(ArrayList<BackStackRecord> records, ArrayList<Boolean> isRecordPop) {
            return FragmentManagerImpl.this.popBackStackState(records, isRecordPop, this.mName, this.mId, this.mFlags);
        }
    }

    /* renamed from: android.support.v4.app.FragmentManagerImpl$StartEnterTransitionListener */
    /* compiled from: FragmentManager */
    static class StartEnterTransitionListener implements OnStartEnterTransitionListener {
        /* access modifiers changed from: private */
        public final boolean mIsBack;
        private int mNumPostponed;
        /* access modifiers changed from: private */
        public final BackStackRecord mRecord;

        StartEnterTransitionListener(BackStackRecord record, boolean isBack) {
            this.mIsBack = isBack;
            this.mRecord = record;
        }

        public void onStartEnterTransition() {
            this.mNumPostponed--;
            if (this.mNumPostponed == 0) {
                this.mRecord.mManager.scheduleCommit();
            }
        }

        public void startListening() {
            this.mNumPostponed++;
        }

        public boolean isReady() {
            return this.mNumPostponed == 0;
        }

        public void completeTransaction() {
            boolean canceled;
            boolean z = false;
            if (this.mNumPostponed > 0) {
                canceled = true;
            } else {
                canceled = false;
            }
            FragmentManagerImpl manager = this.mRecord.mManager;
            int numAdded = manager.mAdded.size();
            for (int i = 0; i < numAdded; i++) {
                Fragment fragment = (Fragment) manager.mAdded.get(i);
                fragment.setOnStartEnterTransitionListener(null);
                if (canceled && fragment.isPostponed()) {
                    fragment.startPostponedEnterTransition();
                }
            }
            FragmentManagerImpl fragmentManagerImpl = this.mRecord.mManager;
            BackStackRecord backStackRecord = this.mRecord;
            boolean z2 = this.mIsBack;
            if (!canceled) {
                z = true;
            }
            fragmentManagerImpl.completeExecute(backStackRecord, z2, z, true);
        }

        public void cancelTransaction() {
            this.mRecord.mManager.completeExecute(this.mRecord, this.mIsBack, false, false);
        }
    }

    FragmentManagerImpl() {
    }

    static {
        boolean z = false;
        if (VERSION.SDK_INT >= 11) {
            z = true;
        }
        HONEYCOMB = z;
    }

    static boolean modifiesAlpha(Animation anim) {
        if (anim instanceof AlphaAnimation) {
            return true;
        }
        if (anim instanceof AnimationSet) {
            List<Animation> anims = ((AnimationSet) anim).getAnimations();
            for (int i = 0; i < anims.size(); i++) {
                if (anims.get(i) instanceof AlphaAnimation) {
                    return true;
                }
            }
        }
        return false;
    }

    static boolean shouldRunOnHWLayer(View v, Animation anim) {
        return VERSION.SDK_INT >= 19 && ViewCompat.getLayerType(v) == 0 && ViewCompat.hasOverlappingRendering(v) && modifiesAlpha(anim);
    }

    private void throwException(RuntimeException ex) {
        Log.e(TAG, ex.getMessage());
        Log.e(TAG, "Activity state:");
        PrintWriter pw = new PrintWriter(new LogWriter(TAG));
        if (this.mHost != null) {
            try {
                this.mHost.onDump("  ", null, pw, new String[0]);
            } catch (Exception e) {
                Log.e(TAG, "Failed dumping state", e);
            }
        } else {
            try {
                dump("  ", null, pw, new String[0]);
            } catch (Exception e2) {
                Log.e(TAG, "Failed dumping state", e2);
            }
        }
        throw ex;
    }

    public FragmentTransaction beginTransaction() {
        return new BackStackRecord(this);
    }

    public boolean executePendingTransactions() {
        boolean updates = execPendingActions();
        forcePostponedTransactions();
        return updates;
    }

    public void popBackStack() {
        enqueueAction(new PopBackStackState(null, -1, 0), false);
    }

    public boolean popBackStackImmediate() {
        checkStateLoss();
        return popBackStackImmediate(null, -1, 0);
    }

    public void popBackStack(String name, int flags) {
        enqueueAction(new PopBackStackState(name, -1, flags), false);
    }

    public boolean popBackStackImmediate(String name, int flags) {
        checkStateLoss();
        return popBackStackImmediate(name, -1, flags);
    }

    public void popBackStack(int id, int flags) {
        if (id < 0) {
            throw new IllegalArgumentException("Bad id: " + id);
        }
        enqueueAction(new PopBackStackState(null, id, flags), false);
    }

    public boolean popBackStackImmediate(int id, int flags) {
        checkStateLoss();
        execPendingActions();
        if (id >= 0) {
            return popBackStackImmediate(null, id, flags);
        }
        throw new IllegalArgumentException("Bad id: " + id);
    }

    private boolean popBackStackImmediate(String name, int id, int flags) {
        execPendingActions();
        ensureExecReady(true);
        boolean executePop = popBackStackState(this.mTmpRecords, this.mTmpIsPop, name, id, flags);
        if (executePop) {
            this.mExecutingActions = true;
            try {
                optimizeAndExecuteOps(this.mTmpRecords, this.mTmpIsPop);
            } finally {
                cleanupExec();
            }
        }
        doPendingDeferredStart();
        return executePop;
    }

    public int getBackStackEntryCount() {
        if (this.mBackStack != null) {
            return this.mBackStack.size();
        }
        return 0;
    }

    public BackStackEntry getBackStackEntryAt(int index) {
        return (BackStackEntry) this.mBackStack.get(index);
    }

    public void addOnBackStackChangedListener(OnBackStackChangedListener listener) {
        if (this.mBackStackChangeListeners == null) {
            this.mBackStackChangeListeners = new ArrayList<>();
        }
        this.mBackStackChangeListeners.add(listener);
    }

    public void removeOnBackStackChangedListener(OnBackStackChangedListener listener) {
        if (this.mBackStackChangeListeners != null) {
            this.mBackStackChangeListeners.remove(listener);
        }
    }

    public void putFragment(Bundle bundle, String key, Fragment fragment) {
        if (fragment.mIndex < 0) {
            throwException(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        }
        bundle.putInt(key, fragment.mIndex);
    }

    public Fragment getFragment(Bundle bundle, String key) {
        int index = bundle.getInt(key, -1);
        if (index == -1) {
            return null;
        }
        if (index >= this.mActive.size()) {
            throwException(new IllegalStateException("Fragment no longer exists for key " + key + ": index " + index));
        }
        Fragment f = (Fragment) this.mActive.get(index);
        if (f != null) {
            return f;
        }
        throwException(new IllegalStateException("Fragment no longer exists for key " + key + ": index " + index));
        return f;
    }

    public List<Fragment> getFragments() {
        return this.mActive;
    }

    public SavedState saveFragmentInstanceState(Fragment fragment) {
        if (fragment.mIndex < 0) {
            throwException(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        }
        if (fragment.mState <= 0) {
            return null;
        }
        Bundle result = saveFragmentBasicState(fragment);
        if (result != null) {
            return new SavedState(result);
        }
        return null;
    }

    public boolean isDestroyed() {
        return this.mDestroyed;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        if (this.mParent != null) {
            DebugUtils.buildShortClassTag(this.mParent, sb);
        } else {
            DebugUtils.buildShortClassTag(this.mHost, sb);
        }
        sb.append("}}");
        return sb.toString();
    }

    public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
        String innerPrefix = prefix + "    ";
        if (this.mActive != null) {
            int N = this.mActive.size();
            if (N > 0) {
                writer.print(prefix);
                writer.print("Active Fragments in ");
                writer.print(Integer.toHexString(System.identityHashCode(this)));
                writer.println(":");
                for (int i = 0; i < N; i++) {
                    Fragment f = (Fragment) this.mActive.get(i);
                    writer.print(prefix);
                    writer.print("  #");
                    writer.print(i);
                    writer.print(": ");
                    writer.println(f);
                    if (f != null) {
                        f.dump(innerPrefix, fd, writer, args);
                    }
                }
            }
        }
        if (this.mAdded != null) {
            int N2 = this.mAdded.size();
            if (N2 > 0) {
                writer.print(prefix);
                writer.println("Added Fragments:");
                for (int i2 = 0; i2 < N2; i2++) {
                    Fragment f2 = (Fragment) this.mAdded.get(i2);
                    writer.print(prefix);
                    writer.print("  #");
                    writer.print(i2);
                    writer.print(": ");
                    writer.println(f2.toString());
                }
            }
        }
        if (this.mCreatedMenus != null) {
            int N3 = this.mCreatedMenus.size();
            if (N3 > 0) {
                writer.print(prefix);
                writer.println("Fragments Created Menus:");
                for (int i3 = 0; i3 < N3; i3++) {
                    Fragment f3 = (Fragment) this.mCreatedMenus.get(i3);
                    writer.print(prefix);
                    writer.print("  #");
                    writer.print(i3);
                    writer.print(": ");
                    writer.println(f3.toString());
                }
            }
        }
        if (this.mBackStack != null) {
            int N4 = this.mBackStack.size();
            if (N4 > 0) {
                writer.print(prefix);
                writer.println("Back Stack:");
                for (int i4 = 0; i4 < N4; i4++) {
                    BackStackRecord bs = (BackStackRecord) this.mBackStack.get(i4);
                    writer.print(prefix);
                    writer.print("  #");
                    writer.print(i4);
                    writer.print(": ");
                    writer.println(bs.toString());
                    bs.dump(innerPrefix, fd, writer, args);
                }
            }
        }
        synchronized (this) {
            if (this.mBackStackIndices != null) {
                int N5 = this.mBackStackIndices.size();
                if (N5 > 0) {
                    writer.print(prefix);
                    writer.println("Back Stack Indices:");
                    for (int i5 = 0; i5 < N5; i5++) {
                        BackStackRecord bs2 = (BackStackRecord) this.mBackStackIndices.get(i5);
                        writer.print(prefix);
                        writer.print("  #");
                        writer.print(i5);
                        writer.print(": ");
                        writer.println(bs2);
                    }
                }
            }
            if (this.mAvailBackStackIndices != null && this.mAvailBackStackIndices.size() > 0) {
                writer.print(prefix);
                writer.print("mAvailBackStackIndices: ");
                writer.println(Arrays.toString(this.mAvailBackStackIndices.toArray()));
            }
        }
        if (this.mPendingActions != null) {
            int N6 = this.mPendingActions.size();
            if (N6 > 0) {
                writer.print(prefix);
                writer.println("Pending Actions:");
                for (int i6 = 0; i6 < N6; i6++) {
                    OpGenerator r = (OpGenerator) this.mPendingActions.get(i6);
                    writer.print(prefix);
                    writer.print("  #");
                    writer.print(i6);
                    writer.print(": ");
                    writer.println(r);
                }
            }
        }
        writer.print(prefix);
        writer.println("FragmentManager misc state:");
        writer.print(prefix);
        writer.print("  mHost=");
        writer.println(this.mHost);
        writer.print(prefix);
        writer.print("  mContainer=");
        writer.println(this.mContainer);
        if (this.mParent != null) {
            writer.print(prefix);
            writer.print("  mParent=");
            writer.println(this.mParent);
        }
        writer.print(prefix);
        writer.print("  mCurState=");
        writer.print(this.mCurState);
        writer.print(" mStateSaved=");
        writer.print(this.mStateSaved);
        writer.print(" mDestroyed=");
        writer.println(this.mDestroyed);
        if (this.mNeedMenuInvalidate) {
            writer.print(prefix);
            writer.print("  mNeedMenuInvalidate=");
            writer.println(this.mNeedMenuInvalidate);
        }
        if (this.mNoTransactionsBecause != null) {
            writer.print(prefix);
            writer.print("  mNoTransactionsBecause=");
            writer.println(this.mNoTransactionsBecause);
        }
        if (this.mAvailIndices != null && this.mAvailIndices.size() > 0) {
            writer.print(prefix);
            writer.print("  mAvailIndices: ");
            writer.println(Arrays.toString(this.mAvailIndices.toArray()));
        }
    }

    static Animation makeOpenCloseAnimation(Context context, float startScale, float endScale, float startAlpha, float endAlpha) {
        AnimationSet set = new AnimationSet(false);
        ScaleAnimation scale = new ScaleAnimation(startScale, endScale, startScale, endScale, 1, 0.5f, 1, 0.5f);
        scale.setInterpolator(DECELERATE_QUINT);
        scale.setDuration(220);
        set.addAnimation(scale);
        AlphaAnimation alpha = new AlphaAnimation(startAlpha, endAlpha);
        alpha.setInterpolator(DECELERATE_CUBIC);
        alpha.setDuration(220);
        set.addAnimation(alpha);
        return set;
    }

    static Animation makeFadeAnimation(Context context, float start, float end) {
        AlphaAnimation anim = new AlphaAnimation(start, end);
        anim.setInterpolator(DECELERATE_CUBIC);
        anim.setDuration(220);
        return anim;
    }

    /* access modifiers changed from: 0000 */
    public Animation loadAnimation(Fragment fragment, int transit, boolean enter, int transitionStyle) {
        Animation animObj = fragment.onCreateAnimation(transit, enter, fragment.getNextAnim());
        if (animObj != null) {
            return animObj;
        }
        if (fragment.getNextAnim() != 0) {
            Animation anim = AnimationUtils.loadAnimation(this.mHost.getContext(), fragment.getNextAnim());
            if (anim != null) {
                return anim;
            }
        }
        if (transit == 0) {
            return null;
        }
        int styleIndex = transitToStyleIndex(transit, enter);
        if (styleIndex < 0) {
            return null;
        }
        switch (styleIndex) {
            case 1:
                return makeOpenCloseAnimation(this.mHost.getContext(), 1.125f, 1.0f, 0.0f, 1.0f);
            case 2:
                return makeOpenCloseAnimation(this.mHost.getContext(), 1.0f, 0.975f, 1.0f, 0.0f);
            case 3:
                return makeOpenCloseAnimation(this.mHost.getContext(), 0.975f, 1.0f, 0.0f, 1.0f);
            case 4:
                return makeOpenCloseAnimation(this.mHost.getContext(), 1.0f, 1.075f, 1.0f, 0.0f);
            case 5:
                return makeFadeAnimation(this.mHost.getContext(), 0.0f, 1.0f);
            case 6:
                return makeFadeAnimation(this.mHost.getContext(), 1.0f, 0.0f);
            default:
                if (transitionStyle == 0 && this.mHost.onHasWindowAnimations()) {
                    transitionStyle = this.mHost.onGetWindowAnimations();
                }
                if (transitionStyle == 0) {
                    return null;
                }
                return null;
        }
    }

    public void performPendingDeferredStart(Fragment f) {
        if (!f.mDeferStart) {
            return;
        }
        if (this.mExecutingActions) {
            this.mHavePendingDeferredStart = true;
            return;
        }
        f.mDeferStart = false;
        moveToState(f, this.mCurState, 0, 0, false);
    }

    private void setHWLayerAnimListenerIfAlpha(View v, Animation anim) {
        if (v != null && anim != null && shouldRunOnHWLayer(v, anim)) {
            AnimationListener originalListener = null;
            try {
                if (sAnimationListenerField == null) {
                    sAnimationListenerField = Animation.class.getDeclaredField("mListener");
                    sAnimationListenerField.setAccessible(true);
                }
                originalListener = (AnimationListener) sAnimationListenerField.get(anim);
            } catch (NoSuchFieldException e) {
                Log.e(TAG, "No field with the name mListener is found in Animation class", e);
            } catch (IllegalAccessException e2) {
                Log.e(TAG, "Cannot access Animation's mListener field", e2);
            }
            ViewCompat.setLayerType(v, 2, null);
            anim.setAnimationListener(new AnimateOnHWLayerIfNeededListener(v, anim, originalListener));
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean isStateAtLeast(int state) {
        return this.mCurState >= state;
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x0464, code lost:
        r14 = android.support.p000v4.p002os.EnvironmentCompat.MEDIA_UNKNOWN;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x0494, code lost:
        if (r19 >= 1) goto L_0x0071;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x049a, code lost:
        if (r17.mDestroyed == false) goto L_0x04af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x04a0, code lost:
        if (r18.getAnimatingAway() == null) goto L_0x04af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x04a2, code lost:
        r15 = r18.getAnimatingAway();
        r18.setAnimatingAway(null);
        r15.clearAnimation();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x04b3, code lost:
        if (r18.getAnimatingAway() == null) goto L_0x060a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x04b5, code lost:
        r18.setStateAfterAnimating(r19);
        r19 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x04ed, code lost:
        if (r19 >= 4) goto L_0x0518;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x04f1, code lost:
        if (DEBUG == false) goto L_0x050d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x04f3, code lost:
        android.util.Log.v(TAG, "movefrom STARTED: " + r18);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x050d, code lost:
        r18.performStop();
        dispatchOnFragmentStopped(r18, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x051b, code lost:
        if (r19 >= 3) goto L_0x053e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x051f, code lost:
        if (DEBUG == false) goto L_0x053b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x0521, code lost:
        android.util.Log.v(TAG, "movefrom STOPPED: " + r18);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x053b, code lost:
        r18.performReallyStop();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x0541, code lost:
        if (r19 >= 2) goto L_0x0491;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x0545, code lost:
        if (DEBUG == false) goto L_0x0561;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x0547, code lost:
        android.util.Log.v(TAG, "movefrom ACTIVITY_CREATED: " + r18);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x0565, code lost:
        if (r18.mView == null) goto L_0x057c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:0x0571, code lost:
        if (r17.mHost.onShouldSaveFragmentState(r18) == false) goto L_0x057c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x0577, code lost:
        if (r18.mSavedViewState != null) goto L_0x057c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:0x0579, code lost:
        saveFragmentViewState(r18);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x057c, code lost:
        r18.performDestroyView();
        dispatchOnFragmentViewDestroyed(r18, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:179:0x058b, code lost:
        if (r18.mView == null) goto L_0x05f9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:0x0591, code lost:
        if (r18.mContainer == null) goto L_0x05f9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:182:0x0593, code lost:
        r10 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:0x0598, code lost:
        if (r17.mCurState <= 0) goto L_0x05c0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:185:0x059e, code lost:
        if (r17.mDestroyed != false) goto L_0x05c0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:187:0x05a8, code lost:
        if (r18.mView.getVisibility() != 0) goto L_0x05c0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:189:0x05b1, code lost:
        if (r18.mPostponedAlpha < 0.0f) goto L_0x05c0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:190:0x05b3, code lost:
        r10 = loadAnimation(r18, r20, false, r21);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:191:0x05c0, code lost:
        r18.mPostponedAlpha = 0.0f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:192:0x05c5, code lost:
        if (r10 == null) goto L_0x05ee;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:193:0x05c7, code lost:
        r13 = r18;
        r18.setAnimatingAway(r18.mView);
        r18.setStateAfterAnimating(r19);
        r10.setAnimationListener(new android.support.p000v4.app.FragmentManagerImpl.C01002(r17, r18.mView, r10));
        r18.mView.startAnimation(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:0x05ee, code lost:
        r18.mContainer.removeView(r18.mView);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x05f9, code lost:
        r18.mContainer = null;
        r18.mView = null;
        r18.mInnerView = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:197:0x060c, code lost:
        if (DEBUG == false) goto L_0x0628;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:198:0x060e, code lost:
        android.util.Log.v(TAG, "movefrom CREATED: " + r18);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:200:0x062c, code lost:
        if (r18.mRetaining != false) goto L_0x0651;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:201:0x062e, code lost:
        r18.performDestroy();
        dispatchOnFragmentDestroyed(r18, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:202:0x0639, code lost:
        r18.performDetach();
        dispatchOnFragmentDetached(r18, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:203:0x0644, code lost:
        if (r22 != false) goto L_0x0071;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:205:0x064a, code lost:
        if (r18.mRetaining != false) goto L_0x0657;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:206:0x064c, code lost:
        makeInactive(r18);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:207:0x0651, code lost:
        r18.mState = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:208:0x0657, code lost:
        r18.mHost = null;
        r18.mParentFragment = null;
        r18.mFragmentManager = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0260, code lost:
        if (r19 <= 1) goto L_0x03c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0264, code lost:
        if (DEBUG == false) goto L_0x0280;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0266, code lost:
        android.util.Log.v(TAG, "moveto ACTIVITY_CREATED: " + r18);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0284, code lost:
        if (r18.mFromLayout != false) goto L_0x0398;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0286, code lost:
        r11 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x028b, code lost:
        if (r18.mContainerId == 0) goto L_0x0316;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0292, code lost:
        if (r18.mContainerId != -1) goto L_0x02b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0294, code lost:
        throwException(new java.lang.IllegalArgumentException("Cannot create fragment " + r18 + " for a container view with no id"));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x02b9, code lost:
        r11 = (android.view.ViewGroup) r17.mContainer.onFindViewById(r18.mContainerId);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x02c7, code lost:
        if (r11 != null) goto L_0x0316;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x02cd, code lost:
        if (r18.mRestored != false) goto L_0x0316;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:?, code lost:
        r14 = r18.getResources().getResourceName(r18.mContainerId);
     */
    /* JADX WARNING: Removed duplicated region for block: B:210:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0079  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void moveToState(android.support.p000v4.app.Fragment r18, int r19, int r20, int r21, boolean r22) {
        /*
            r17 = this;
            r0 = r18
            boolean r4 = r0.mAdded
            if (r4 == 0) goto L_0x000c
            r0 = r18
            boolean r4 = r0.mDetached
            if (r4 == 0) goto L_0x0013
        L_0x000c:
            r4 = 1
            r0 = r19
            if (r0 <= r4) goto L_0x0013
            r19 = 1
        L_0x0013:
            r0 = r18
            boolean r4 = r0.mRemoving
            if (r4 == 0) goto L_0x0027
            r0 = r18
            int r4 = r0.mState
            r0 = r19
            if (r0 <= r4) goto L_0x0027
            r0 = r18
            int r0 = r0.mState
            r19 = r0
        L_0x0027:
            r0 = r18
            boolean r4 = r0.mDeferStart
            if (r4 == 0) goto L_0x003b
            r0 = r18
            int r4 = r0.mState
            r5 = 4
            if (r4 >= r5) goto L_0x003b
            r4 = 3
            r0 = r19
            if (r0 <= r4) goto L_0x003b
            r19 = 3
        L_0x003b:
            r0 = r18
            int r4 = r0.mState
            r0 = r19
            if (r4 >= r0) goto L_0x0480
            r0 = r18
            boolean r4 = r0.mFromLayout
            if (r4 == 0) goto L_0x0050
            r0 = r18
            boolean r4 = r0.mInLayout
            if (r4 != 0) goto L_0x0050
        L_0x004f:
            return
        L_0x0050:
            android.view.View r4 = r18.getAnimatingAway()
            if (r4 == 0) goto L_0x006a
            r4 = 0
            r0 = r18
            r0.setAnimatingAway(r4)
            int r6 = r18.getStateAfterAnimating()
            r7 = 0
            r8 = 0
            r9 = 1
            r4 = r17
            r5 = r18
            r4.moveToState(r5, r6, r7, r8, r9)
        L_0x006a:
            r0 = r18
            int r4 = r0.mState
            switch(r4) {
                case 0: goto L_0x00ba;
                case 1: goto L_0x025d;
                case 2: goto L_0x03c1;
                case 3: goto L_0x03cb;
                case 4: goto L_0x03f9;
                default: goto L_0x0071;
            }
        L_0x0071:
            r0 = r18
            int r4 = r0.mState
            r0 = r19
            if (r4 == r0) goto L_0x004f
            java.lang.String r4 = "FragmentManager"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "moveToState: Fragment state for "
            java.lang.StringBuilder r5 = r5.append(r6)
            r0 = r18
            java.lang.StringBuilder r5 = r5.append(r0)
            java.lang.String r6 = " not updated inline; "
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r6 = "expected state "
            java.lang.StringBuilder r5 = r5.append(r6)
            r0 = r19
            java.lang.StringBuilder r5 = r5.append(r0)
            java.lang.String r6 = " found "
            java.lang.StringBuilder r5 = r5.append(r6)
            r0 = r18
            int r6 = r0.mState
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            android.util.Log.w(r4, r5)
            r0 = r19
            r1 = r18
            r1.mState = r0
            goto L_0x004f
        L_0x00ba:
            boolean r4 = DEBUG
            if (r4 == 0) goto L_0x00d8
            java.lang.String r4 = "FragmentManager"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "moveto CREATED: "
            java.lang.StringBuilder r5 = r5.append(r6)
            r0 = r18
            java.lang.StringBuilder r5 = r5.append(r0)
            java.lang.String r5 = r5.toString()
            android.util.Log.v(r4, r5)
        L_0x00d8:
            r0 = r18
            android.os.Bundle r4 = r0.mSavedFragmentState
            if (r4 == 0) goto L_0x0145
            r0 = r18
            android.os.Bundle r4 = r0.mSavedFragmentState
            r0 = r17
            android.support.v4.app.FragmentHostCallback r5 = r0.mHost
            android.content.Context r5 = r5.getContext()
            java.lang.ClassLoader r5 = r5.getClassLoader()
            r4.setClassLoader(r5)
            r0 = r18
            android.os.Bundle r4 = r0.mSavedFragmentState
            java.lang.String r5 = "android:view_state"
            android.util.SparseArray r4 = r4.getSparseParcelableArray(r5)
            r0 = r18
            r0.mSavedViewState = r4
            r0 = r18
            android.os.Bundle r4 = r0.mSavedFragmentState
            java.lang.String r5 = "android:target_state"
            r0 = r17
            android.support.v4.app.Fragment r4 = r0.getFragment(r4, r5)
            r0 = r18
            r0.mTarget = r4
            r0 = r18
            android.support.v4.app.Fragment r4 = r0.mTarget
            if (r4 == 0) goto L_0x0124
            r0 = r18
            android.os.Bundle r4 = r0.mSavedFragmentState
            java.lang.String r5 = "android:target_req_state"
            r6 = 0
            int r4 = r4.getInt(r5, r6)
            r0 = r18
            r0.mTargetRequestCode = r4
        L_0x0124:
            r0 = r18
            android.os.Bundle r4 = r0.mSavedFragmentState
            java.lang.String r5 = "android:user_visible_hint"
            r6 = 1
            boolean r4 = r4.getBoolean(r5, r6)
            r0 = r18
            r0.mUserVisibleHint = r4
            r0 = r18
            boolean r4 = r0.mUserVisibleHint
            if (r4 != 0) goto L_0x0145
            r4 = 1
            r0 = r18
            r0.mDeferStart = r4
            r4 = 3
            r0 = r19
            if (r0 <= r4) goto L_0x0145
            r19 = 3
        L_0x0145:
            r0 = r17
            android.support.v4.app.FragmentHostCallback r4 = r0.mHost
            r0 = r18
            r0.mHost = r4
            r0 = r17
            android.support.v4.app.Fragment r4 = r0.mParent
            r0 = r18
            r0.mParentFragment = r4
            r0 = r17
            android.support.v4.app.Fragment r4 = r0.mParent
            if (r4 == 0) goto L_0x01ae
            r0 = r17
            android.support.v4.app.Fragment r4 = r0.mParent
            android.support.v4.app.FragmentManagerImpl r4 = r4.mChildFragmentManager
        L_0x0161:
            r0 = r18
            r0.mFragmentManager = r4
            r0 = r17
            android.support.v4.app.FragmentHostCallback r4 = r0.mHost
            android.content.Context r4 = r4.getContext()
            r5 = 0
            r0 = r17
            r1 = r18
            r0.dispatchOnFragmentPreAttached(r1, r4, r5)
            r4 = 0
            r0 = r18
            r0.mCalled = r4
            r0 = r17
            android.support.v4.app.FragmentHostCallback r4 = r0.mHost
            android.content.Context r4 = r4.getContext()
            r0 = r18
            r0.onAttach(r4)
            r0 = r18
            boolean r4 = r0.mCalled
            if (r4 != 0) goto L_0x01b7
            android.support.v4.app.SuperNotCalledException r4 = new android.support.v4.app.SuperNotCalledException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Fragment "
            java.lang.StringBuilder r5 = r5.append(r6)
            r0 = r18
            java.lang.StringBuilder r5 = r5.append(r0)
            java.lang.String r6 = " did not call through to super.onAttach()"
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            r4.<init>(r5)
            throw r4
        L_0x01ae:
            r0 = r17
            android.support.v4.app.FragmentHostCallback r4 = r0.mHost
            android.support.v4.app.FragmentManagerImpl r4 = r4.getFragmentManagerImpl()
            goto L_0x0161
        L_0x01b7:
            r0 = r18
            android.support.v4.app.Fragment r4 = r0.mParentFragment
            if (r4 != 0) goto L_0x0433
            r0 = r17
            android.support.v4.app.FragmentHostCallback r4 = r0.mHost
            r0 = r18
            r4.onAttachFragment(r0)
        L_0x01c6:
            r0 = r17
            android.support.v4.app.FragmentHostCallback r4 = r0.mHost
            android.content.Context r4 = r4.getContext()
            r5 = 0
            r0 = r17
            r1 = r18
            r0.dispatchOnFragmentAttached(r1, r4, r5)
            r0 = r18
            boolean r4 = r0.mRetaining
            if (r4 != 0) goto L_0x043e
            r0 = r18
            android.os.Bundle r4 = r0.mSavedFragmentState
            r0 = r18
            r0.performCreate(r4)
            r0 = r18
            android.os.Bundle r4 = r0.mSavedFragmentState
            r5 = 0
            r0 = r17
            r1 = r18
            r0.dispatchOnFragmentCreated(r1, r4, r5)
        L_0x01f1:
            r4 = 0
            r0 = r18
            r0.mRetaining = r4
            r0 = r18
            boolean r4 = r0.mFromLayout
            if (r4 == 0) goto L_0x025d
            r0 = r18
            android.os.Bundle r4 = r0.mSavedFragmentState
            r0 = r18
            android.view.LayoutInflater r4 = r0.getLayoutInflater(r4)
            r5 = 0
            r0 = r18
            android.os.Bundle r6 = r0.mSavedFragmentState
            r0 = r18
            android.view.View r4 = r0.performCreateView(r4, r5, r6)
            r0 = r18
            r0.mView = r4
            r0 = r18
            android.view.View r4 = r0.mView
            if (r4 == 0) goto L_0x045c
            r0 = r18
            android.view.View r4 = r0.mView
            r0 = r18
            r0.mInnerView = r4
            int r4 = android.os.Build.VERSION.SDK_INT
            r5 = 11
            if (r4 < r5) goto L_0x044e
            r0 = r18
            android.view.View r4 = r0.mView
            r5 = 0
            android.support.p000v4.view.ViewCompat.setSaveFromParentEnabled(r4, r5)
        L_0x0231:
            r0 = r18
            boolean r4 = r0.mHidden
            if (r4 == 0) goto L_0x0240
            r0 = r18
            android.view.View r4 = r0.mView
            r5 = 8
            r4.setVisibility(r5)
        L_0x0240:
            r0 = r18
            android.view.View r4 = r0.mView
            r0 = r18
            android.os.Bundle r5 = r0.mSavedFragmentState
            r0 = r18
            r0.onViewCreated(r4, r5)
            r0 = r18
            android.view.View r4 = r0.mView
            r0 = r18
            android.os.Bundle r5 = r0.mSavedFragmentState
            r6 = 0
            r0 = r17
            r1 = r18
            r0.dispatchOnFragmentViewCreated(r1, r4, r5, r6)
        L_0x025d:
            r4 = 1
            r0 = r19
            if (r0 <= r4) goto L_0x03c1
            boolean r4 = DEBUG
            if (r4 == 0) goto L_0x0280
            java.lang.String r4 = "FragmentManager"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "moveto ACTIVITY_CREATED: "
            java.lang.StringBuilder r5 = r5.append(r6)
            r0 = r18
            java.lang.StringBuilder r5 = r5.append(r0)
            java.lang.String r5 = r5.toString()
            android.util.Log.v(r4, r5)
        L_0x0280:
            r0 = r18
            boolean r4 = r0.mFromLayout
            if (r4 != 0) goto L_0x0398
            r11 = 0
            r0 = r18
            int r4 = r0.mContainerId
            if (r4 == 0) goto L_0x0316
            r0 = r18
            int r4 = r0.mContainerId
            r5 = -1
            if (r4 != r5) goto L_0x02b9
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Cannot create fragment "
            java.lang.StringBuilder r5 = r5.append(r6)
            r0 = r18
            java.lang.StringBuilder r5 = r5.append(r0)
            java.lang.String r6 = " for a container view with no id"
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            r4.<init>(r5)
            r0 = r17
            r0.throwException(r4)
        L_0x02b9:
            r0 = r17
            android.support.v4.app.FragmentContainer r4 = r0.mContainer
            r0 = r18
            int r5 = r0.mContainerId
            android.view.View r11 = r4.onFindViewById(r5)
            android.view.ViewGroup r11 = (android.view.ViewGroup) r11
            if (r11 != 0) goto L_0x0316
            r0 = r18
            boolean r4 = r0.mRestored
            if (r4 != 0) goto L_0x0316
            android.content.res.Resources r4 = r18.getResources()     // Catch:{ NotFoundException -> 0x0463 }
            r0 = r18
            int r5 = r0.mContainerId     // Catch:{ NotFoundException -> 0x0463 }
            java.lang.String r14 = r4.getResourceName(r5)     // Catch:{ NotFoundException -> 0x0463 }
        L_0x02db:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "No view found for id 0x"
            java.lang.StringBuilder r5 = r5.append(r6)
            r0 = r18
            int r6 = r0.mContainerId
            java.lang.String r6 = java.lang.Integer.toHexString(r6)
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r6 = " ("
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.StringBuilder r5 = r5.append(r14)
            java.lang.String r6 = ") for fragment "
            java.lang.StringBuilder r5 = r5.append(r6)
            r0 = r18
            java.lang.StringBuilder r5 = r5.append(r0)
            java.lang.String r5 = r5.toString()
            r4.<init>(r5)
            r0 = r17
            r0.throwException(r4)
        L_0x0316:
            r0 = r18
            r0.mContainer = r11
            r0 = r18
            android.os.Bundle r4 = r0.mSavedFragmentState
            r0 = r18
            android.view.LayoutInflater r4 = r0.getLayoutInflater(r4)
            r0 = r18
            android.os.Bundle r5 = r0.mSavedFragmentState
            r0 = r18
            android.view.View r4 = r0.performCreateView(r4, r11, r5)
            r0 = r18
            r0.mView = r4
            r0 = r18
            android.view.View r4 = r0.mView
            if (r4 == 0) goto L_0x0479
            r0 = r18
            android.view.View r4 = r0.mView
            r0 = r18
            r0.mInnerView = r4
            int r4 = android.os.Build.VERSION.SDK_INT
            r5 = 11
            if (r4 < r5) goto L_0x0468
            r0 = r18
            android.view.View r4 = r0.mView
            r5 = 0
            android.support.p000v4.view.ViewCompat.setSaveFromParentEnabled(r4, r5)
        L_0x034e:
            if (r11 == 0) goto L_0x0357
            r0 = r18
            android.view.View r4 = r0.mView
            r11.addView(r4)
        L_0x0357:
            r0 = r18
            boolean r4 = r0.mHidden
            if (r4 == 0) goto L_0x0366
            r0 = r18
            android.view.View r4 = r0.mView
            r5 = 8
            r4.setVisibility(r5)
        L_0x0366:
            r0 = r18
            android.view.View r4 = r0.mView
            r0 = r18
            android.os.Bundle r5 = r0.mSavedFragmentState
            r0 = r18
            r0.onViewCreated(r4, r5)
            r0 = r18
            android.view.View r4 = r0.mView
            r0 = r18
            android.os.Bundle r5 = r0.mSavedFragmentState
            r6 = 0
            r0 = r17
            r1 = r18
            r0.dispatchOnFragmentViewCreated(r1, r4, r5, r6)
            r0 = r18
            android.view.View r4 = r0.mView
            int r4 = r4.getVisibility()
            if (r4 != 0) goto L_0x0476
            r0 = r18
            android.view.ViewGroup r4 = r0.mContainer
            if (r4 == 0) goto L_0x0476
            r4 = 1
        L_0x0394:
            r0 = r18
            r0.mIsNewlyAdded = r4
        L_0x0398:
            r0 = r18
            android.os.Bundle r4 = r0.mSavedFragmentState
            r0 = r18
            r0.performActivityCreated(r4)
            r0 = r18
            android.os.Bundle r4 = r0.mSavedFragmentState
            r5 = 0
            r0 = r17
            r1 = r18
            r0.dispatchOnFragmentActivityCreated(r1, r4, r5)
            r0 = r18
            android.view.View r4 = r0.mView
            if (r4 == 0) goto L_0x03bc
            r0 = r18
            android.os.Bundle r4 = r0.mSavedFragmentState
            r0 = r18
            r0.restoreViewState(r4)
        L_0x03bc:
            r4 = 0
            r0 = r18
            r0.mSavedFragmentState = r4
        L_0x03c1:
            r4 = 2
            r0 = r19
            if (r0 <= r4) goto L_0x03cb
            r4 = 3
            r0 = r18
            r0.mState = r4
        L_0x03cb:
            r4 = 3
            r0 = r19
            if (r0 <= r4) goto L_0x03f9
            boolean r4 = DEBUG
            if (r4 == 0) goto L_0x03ee
            java.lang.String r4 = "FragmentManager"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "moveto STARTED: "
            java.lang.StringBuilder r5 = r5.append(r6)
            r0 = r18
            java.lang.StringBuilder r5 = r5.append(r0)
            java.lang.String r5 = r5.toString()
            android.util.Log.v(r4, r5)
        L_0x03ee:
            r18.performStart()
            r4 = 0
            r0 = r17
            r1 = r18
            r0.dispatchOnFragmentStarted(r1, r4)
        L_0x03f9:
            r4 = 4
            r0 = r19
            if (r0 <= r4) goto L_0x0071
            boolean r4 = DEBUG
            if (r4 == 0) goto L_0x041c
            java.lang.String r4 = "FragmentManager"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "moveto RESUMED: "
            java.lang.StringBuilder r5 = r5.append(r6)
            r0 = r18
            java.lang.StringBuilder r5 = r5.append(r0)
            java.lang.String r5 = r5.toString()
            android.util.Log.v(r4, r5)
        L_0x041c:
            r18.performResume()
            r4 = 0
            r0 = r17
            r1 = r18
            r0.dispatchOnFragmentResumed(r1, r4)
            r4 = 0
            r0 = r18
            r0.mSavedFragmentState = r4
            r4 = 0
            r0 = r18
            r0.mSavedViewState = r4
            goto L_0x0071
        L_0x0433:
            r0 = r18
            android.support.v4.app.Fragment r4 = r0.mParentFragment
            r0 = r18
            r4.onAttachFragment(r0)
            goto L_0x01c6
        L_0x043e:
            r0 = r18
            android.os.Bundle r4 = r0.mSavedFragmentState
            r0 = r18
            r0.restoreChildFragmentState(r4)
            r4 = 1
            r0 = r18
            r0.mState = r4
            goto L_0x01f1
        L_0x044e:
            r0 = r18
            android.view.View r4 = r0.mView
            android.view.ViewGroup r4 = android.support.p000v4.app.NoSaveStateFrameLayout.wrap(r4)
            r0 = r18
            r0.mView = r4
            goto L_0x0231
        L_0x045c:
            r4 = 0
            r0 = r18
            r0.mInnerView = r4
            goto L_0x025d
        L_0x0463:
            r12 = move-exception
            java.lang.String r14 = "unknown"
            goto L_0x02db
        L_0x0468:
            r0 = r18
            android.view.View r4 = r0.mView
            android.view.ViewGroup r4 = android.support.p000v4.app.NoSaveStateFrameLayout.wrap(r4)
            r0 = r18
            r0.mView = r4
            goto L_0x034e
        L_0x0476:
            r4 = 0
            goto L_0x0394
        L_0x0479:
            r4 = 0
            r0 = r18
            r0.mInnerView = r4
            goto L_0x0398
        L_0x0480:
            r0 = r18
            int r4 = r0.mState
            r0 = r19
            if (r4 <= r0) goto L_0x0071
            r0 = r18
            int r4 = r0.mState
            switch(r4) {
                case 1: goto L_0x0491;
                case 2: goto L_0x053e;
                case 3: goto L_0x0518;
                case 4: goto L_0x04ea;
                case 5: goto L_0x04bc;
                default: goto L_0x048f;
            }
        L_0x048f:
            goto L_0x0071
        L_0x0491:
            r4 = 1
            r0 = r19
            if (r0 >= r4) goto L_0x0071
            r0 = r17
            boolean r4 = r0.mDestroyed
            if (r4 == 0) goto L_0x04af
            android.view.View r4 = r18.getAnimatingAway()
            if (r4 == 0) goto L_0x04af
            android.view.View r15 = r18.getAnimatingAway()
            r4 = 0
            r0 = r18
            r0.setAnimatingAway(r4)
            r15.clearAnimation()
        L_0x04af:
            android.view.View r4 = r18.getAnimatingAway()
            if (r4 == 0) goto L_0x060a
            r18.setStateAfterAnimating(r19)
            r19 = 1
            goto L_0x0071
        L_0x04bc:
            r4 = 5
            r0 = r19
            if (r0 >= r4) goto L_0x04ea
            boolean r4 = DEBUG
            if (r4 == 0) goto L_0x04df
            java.lang.String r4 = "FragmentManager"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "movefrom RESUMED: "
            java.lang.StringBuilder r5 = r5.append(r6)
            r0 = r18
            java.lang.StringBuilder r5 = r5.append(r0)
            java.lang.String r5 = r5.toString()
            android.util.Log.v(r4, r5)
        L_0x04df:
            r18.performPause()
            r4 = 0
            r0 = r17
            r1 = r18
            r0.dispatchOnFragmentPaused(r1, r4)
        L_0x04ea:
            r4 = 4
            r0 = r19
            if (r0 >= r4) goto L_0x0518
            boolean r4 = DEBUG
            if (r4 == 0) goto L_0x050d
            java.lang.String r4 = "FragmentManager"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "movefrom STARTED: "
            java.lang.StringBuilder r5 = r5.append(r6)
            r0 = r18
            java.lang.StringBuilder r5 = r5.append(r0)
            java.lang.String r5 = r5.toString()
            android.util.Log.v(r4, r5)
        L_0x050d:
            r18.performStop()
            r4 = 0
            r0 = r17
            r1 = r18
            r0.dispatchOnFragmentStopped(r1, r4)
        L_0x0518:
            r4 = 3
            r0 = r19
            if (r0 >= r4) goto L_0x053e
            boolean r4 = DEBUG
            if (r4 == 0) goto L_0x053b
            java.lang.String r4 = "FragmentManager"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "movefrom STOPPED: "
            java.lang.StringBuilder r5 = r5.append(r6)
            r0 = r18
            java.lang.StringBuilder r5 = r5.append(r0)
            java.lang.String r5 = r5.toString()
            android.util.Log.v(r4, r5)
        L_0x053b:
            r18.performReallyStop()
        L_0x053e:
            r4 = 2
            r0 = r19
            if (r0 >= r4) goto L_0x0491
            boolean r4 = DEBUG
            if (r4 == 0) goto L_0x0561
            java.lang.String r4 = "FragmentManager"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "movefrom ACTIVITY_CREATED: "
            java.lang.StringBuilder r5 = r5.append(r6)
            r0 = r18
            java.lang.StringBuilder r5 = r5.append(r0)
            java.lang.String r5 = r5.toString()
            android.util.Log.v(r4, r5)
        L_0x0561:
            r0 = r18
            android.view.View r4 = r0.mView
            if (r4 == 0) goto L_0x057c
            r0 = r17
            android.support.v4.app.FragmentHostCallback r4 = r0.mHost
            r0 = r18
            boolean r4 = r4.onShouldSaveFragmentState(r0)
            if (r4 == 0) goto L_0x057c
            r0 = r18
            android.util.SparseArray<android.os.Parcelable> r4 = r0.mSavedViewState
            if (r4 != 0) goto L_0x057c
            r17.saveFragmentViewState(r18)
        L_0x057c:
            r18.performDestroyView()
            r4 = 0
            r0 = r17
            r1 = r18
            r0.dispatchOnFragmentViewDestroyed(r1, r4)
            r0 = r18
            android.view.View r4 = r0.mView
            if (r4 == 0) goto L_0x05f9
            r0 = r18
            android.view.ViewGroup r4 = r0.mContainer
            if (r4 == 0) goto L_0x05f9
            r10 = 0
            r0 = r17
            int r4 = r0.mCurState
            if (r4 <= 0) goto L_0x05c0
            r0 = r17
            boolean r4 = r0.mDestroyed
            if (r4 != 0) goto L_0x05c0
            r0 = r18
            android.view.View r4 = r0.mView
            int r4 = r4.getVisibility()
            if (r4 != 0) goto L_0x05c0
            r0 = r18
            float r4 = r0.mPostponedAlpha
            r5 = 0
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 < 0) goto L_0x05c0
            r4 = 0
            r0 = r17
            r1 = r18
            r2 = r20
            r3 = r21
            android.view.animation.Animation r10 = r0.loadAnimation(r1, r2, r4, r3)
        L_0x05c0:
            r4 = 0
            r0 = r18
            r0.mPostponedAlpha = r4
            if (r10 == 0) goto L_0x05ee
            r13 = r18
            r0 = r18
            android.view.View r4 = r0.mView
            r0 = r18
            r0.setAnimatingAway(r4)
            r18.setStateAfterAnimating(r19)
            r0 = r18
            android.view.View r0 = r0.mView
            r16 = r0
            android.support.v4.app.FragmentManagerImpl$2 r4 = new android.support.v4.app.FragmentManagerImpl$2
            r0 = r17
            r1 = r16
            r4.<init>(r1, r10, r13)
            r10.setAnimationListener(r4)
            r0 = r18
            android.view.View r4 = r0.mView
            r4.startAnimation(r10)
        L_0x05ee:
            r0 = r18
            android.view.ViewGroup r4 = r0.mContainer
            r0 = r18
            android.view.View r5 = r0.mView
            r4.removeView(r5)
        L_0x05f9:
            r4 = 0
            r0 = r18
            r0.mContainer = r4
            r4 = 0
            r0 = r18
            r0.mView = r4
            r4 = 0
            r0 = r18
            r0.mInnerView = r4
            goto L_0x0491
        L_0x060a:
            boolean r4 = DEBUG
            if (r4 == 0) goto L_0x0628
            java.lang.String r4 = "FragmentManager"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "movefrom CREATED: "
            java.lang.StringBuilder r5 = r5.append(r6)
            r0 = r18
            java.lang.StringBuilder r5 = r5.append(r0)
            java.lang.String r5 = r5.toString()
            android.util.Log.v(r4, r5)
        L_0x0628:
            r0 = r18
            boolean r4 = r0.mRetaining
            if (r4 != 0) goto L_0x0651
            r18.performDestroy()
            r4 = 0
            r0 = r17
            r1 = r18
            r0.dispatchOnFragmentDestroyed(r1, r4)
        L_0x0639:
            r18.performDetach()
            r4 = 0
            r0 = r17
            r1 = r18
            r0.dispatchOnFragmentDetached(r1, r4)
            if (r22 != 0) goto L_0x0071
            r0 = r18
            boolean r4 = r0.mRetaining
            if (r4 != 0) goto L_0x0657
            r17.makeInactive(r18)
            goto L_0x0071
        L_0x0651:
            r4 = 0
            r0 = r18
            r0.mState = r4
            goto L_0x0639
        L_0x0657:
            r4 = 0
            r0 = r18
            r0.mHost = r4
            r4 = 0
            r0 = r18
            r0.mParentFragment = r4
            r4 = 0
            r0 = r18
            r0.mFragmentManager = r4
            goto L_0x0071
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.app.FragmentManagerImpl.moveToState(android.support.v4.app.Fragment, int, int, int, boolean):void");
    }

    /* access modifiers changed from: 0000 */
    public void moveToState(Fragment f) {
        moveToState(f, this.mCurState, 0, 0, false);
    }

    /* access modifiers changed from: 0000 */
    public void completeShowHideFragment(Fragment fragment) {
        int visibility;
        if (fragment.mView != null) {
            Animation anim = loadAnimation(fragment, fragment.getNextTransition(), !fragment.mHidden, fragment.getNextTransitionStyle());
            if (anim != null) {
                setHWLayerAnimListenerIfAlpha(fragment.mView, anim);
                fragment.mView.startAnimation(anim);
                setHWLayerAnimListenerIfAlpha(fragment.mView, anim);
                anim.start();
            }
            if (!fragment.mHidden || fragment.isHideReplaced()) {
                visibility = 0;
            } else {
                visibility = 8;
            }
            fragment.mView.setVisibility(visibility);
            if (fragment.isHideReplaced()) {
                fragment.setHideReplaced(false);
            }
        }
        if (fragment.mAdded && fragment.mHasMenu && fragment.mMenuVisible) {
            this.mNeedMenuInvalidate = true;
        }
        fragment.mHiddenChanged = false;
        fragment.onHiddenChanged(fragment.mHidden);
    }

    /* access modifiers changed from: 0000 */
    public void moveFragmentToExpectedState(Fragment f) {
        if (f != null) {
            int nextState = this.mCurState;
            if (f.mRemoving) {
                if (f.isInBackStack()) {
                    nextState = Math.min(nextState, 1);
                } else {
                    nextState = Math.min(nextState, 0);
                }
            }
            moveToState(f, nextState, f.getNextTransition(), f.getNextTransitionStyle(), false);
            if (f.mView != null) {
                Fragment underFragment = findFragmentUnder(f);
                if (underFragment != null) {
                    View underView = underFragment.mView;
                    ViewGroup container = f.mContainer;
                    int underIndex = container.indexOfChild(underView);
                    int viewIndex = container.indexOfChild(f.mView);
                    if (viewIndex < underIndex) {
                        container.removeViewAt(viewIndex);
                        container.addView(f.mView, underIndex);
                    }
                }
                if (f.mIsNewlyAdded && f.mContainer != null) {
                    if (VERSION.SDK_INT < 11) {
                        f.mView.setVisibility(0);
                    } else if (f.mPostponedAlpha > 0.0f) {
                        f.mView.setAlpha(f.mPostponedAlpha);
                    }
                    f.mPostponedAlpha = 0.0f;
                    f.mIsNewlyAdded = false;
                    Animation anim = loadAnimation(f, f.getNextTransition(), true, f.getNextTransitionStyle());
                    if (anim != null) {
                        setHWLayerAnimListenerIfAlpha(f.mView, anim);
                        f.mView.startAnimation(anim);
                    }
                }
            }
            if (f.mHiddenChanged) {
                completeShowHideFragment(f);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void moveToState(int newState, boolean always) {
        if (this.mHost == null && newState != 0) {
            throw new IllegalStateException("No activity");
        } else if (always || newState != this.mCurState) {
            this.mCurState = newState;
            if (this.mActive != null) {
                boolean loadersRunning = false;
                if (this.mAdded != null) {
                    int numAdded = this.mAdded.size();
                    for (int i = 0; i < numAdded; i++) {
                        Fragment f = (Fragment) this.mAdded.get(i);
                        moveFragmentToExpectedState(f);
                        if (f.mLoaderManager != null) {
                            loadersRunning |= f.mLoaderManager.hasRunningLoaders();
                        }
                    }
                }
                int numActive = this.mActive.size();
                for (int i2 = 0; i2 < numActive; i2++) {
                    Fragment f2 = (Fragment) this.mActive.get(i2);
                    if (f2 != null && ((f2.mRemoving || f2.mDetached) && !f2.mIsNewlyAdded)) {
                        moveFragmentToExpectedState(f2);
                        if (f2.mLoaderManager != null) {
                            loadersRunning |= f2.mLoaderManager.hasRunningLoaders();
                        }
                    }
                }
                if (!loadersRunning) {
                    startPendingDeferredFragments();
                }
                if (this.mNeedMenuInvalidate && this.mHost != null && this.mCurState == 5) {
                    this.mHost.onSupportInvalidateOptionsMenu();
                    this.mNeedMenuInvalidate = false;
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void startPendingDeferredFragments() {
        if (this.mActive != null) {
            for (int i = 0; i < this.mActive.size(); i++) {
                Fragment f = (Fragment) this.mActive.get(i);
                if (f != null) {
                    performPendingDeferredStart(f);
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void makeActive(Fragment f) {
        if (f.mIndex < 0) {
            if (this.mAvailIndices == null || this.mAvailIndices.size() <= 0) {
                if (this.mActive == null) {
                    this.mActive = new ArrayList<>();
                }
                f.setIndex(this.mActive.size(), this.mParent);
                this.mActive.add(f);
            } else {
                f.setIndex(((Integer) this.mAvailIndices.remove(this.mAvailIndices.size() - 1)).intValue(), this.mParent);
                this.mActive.set(f.mIndex, f);
            }
            if (DEBUG) {
                Log.v(TAG, "Allocated fragment index " + f);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void makeInactive(Fragment f) {
        if (f.mIndex >= 0) {
            if (DEBUG) {
                Log.v(TAG, "Freeing fragment index " + f);
            }
            this.mActive.set(f.mIndex, null);
            if (this.mAvailIndices == null) {
                this.mAvailIndices = new ArrayList<>();
            }
            this.mAvailIndices.add(Integer.valueOf(f.mIndex));
            this.mHost.inactivateFragment(f.mWho);
            f.initState();
        }
    }

    public void addFragment(Fragment fragment, boolean moveToStateNow) {
        if (this.mAdded == null) {
            this.mAdded = new ArrayList<>();
        }
        if (DEBUG) {
            Log.v(TAG, "add: " + fragment);
        }
        makeActive(fragment);
        if (fragment.mDetached) {
            return;
        }
        if (this.mAdded.contains(fragment)) {
            throw new IllegalStateException("Fragment already added: " + fragment);
        }
        this.mAdded.add(fragment);
        fragment.mAdded = true;
        fragment.mRemoving = false;
        if (fragment.mView == null) {
            fragment.mHiddenChanged = false;
        }
        if (fragment.mHasMenu && fragment.mMenuVisible) {
            this.mNeedMenuInvalidate = true;
        }
        if (moveToStateNow) {
            moveToState(fragment);
        }
    }

    public void removeFragment(Fragment fragment) {
        boolean inactive;
        if (DEBUG) {
            Log.v(TAG, "remove: " + fragment + " nesting=" + fragment.mBackStackNesting);
        }
        if (!fragment.isInBackStack()) {
            inactive = true;
        } else {
            inactive = false;
        }
        if (!fragment.mDetached || inactive) {
            if (this.mAdded != null) {
                this.mAdded.remove(fragment);
            }
            if (fragment.mHasMenu && fragment.mMenuVisible) {
                this.mNeedMenuInvalidate = true;
            }
            fragment.mAdded = false;
            fragment.mRemoving = true;
        }
    }

    public void hideFragment(Fragment fragment) {
        boolean z = true;
        if (DEBUG) {
            Log.v(TAG, "hide: " + fragment);
        }
        if (!fragment.mHidden) {
            fragment.mHidden = true;
            if (fragment.mHiddenChanged) {
                z = false;
            }
            fragment.mHiddenChanged = z;
        }
    }

    public void showFragment(Fragment fragment) {
        boolean z = false;
        if (DEBUG) {
            Log.v(TAG, "show: " + fragment);
        }
        if (fragment.mHidden) {
            fragment.mHidden = false;
            if (!fragment.mHiddenChanged) {
                z = true;
            }
            fragment.mHiddenChanged = z;
        }
    }

    public void detachFragment(Fragment fragment) {
        if (DEBUG) {
            Log.v(TAG, "detach: " + fragment);
        }
        if (!fragment.mDetached) {
            fragment.mDetached = true;
            if (fragment.mAdded) {
                if (this.mAdded != null) {
                    if (DEBUG) {
                        Log.v(TAG, "remove from detach: " + fragment);
                    }
                    this.mAdded.remove(fragment);
                }
                if (fragment.mHasMenu && fragment.mMenuVisible) {
                    this.mNeedMenuInvalidate = true;
                }
                fragment.mAdded = false;
            }
        }
    }

    public void attachFragment(Fragment fragment) {
        if (DEBUG) {
            Log.v(TAG, "attach: " + fragment);
        }
        if (fragment.mDetached) {
            fragment.mDetached = false;
            if (!fragment.mAdded) {
                if (this.mAdded == null) {
                    this.mAdded = new ArrayList<>();
                }
                if (this.mAdded.contains(fragment)) {
                    throw new IllegalStateException("Fragment already added: " + fragment);
                }
                if (DEBUG) {
                    Log.v(TAG, "add from attach: " + fragment);
                }
                this.mAdded.add(fragment);
                fragment.mAdded = true;
                if (fragment.mHasMenu && fragment.mMenuVisible) {
                    this.mNeedMenuInvalidate = true;
                }
            }
        }
    }

    public Fragment findFragmentById(int id) {
        if (this.mAdded != null) {
            for (int i = this.mAdded.size() - 1; i >= 0; i--) {
                Fragment f = (Fragment) this.mAdded.get(i);
                if (f != null && f.mFragmentId == id) {
                    return f;
                }
            }
        }
        if (this.mActive != null) {
            for (int i2 = this.mActive.size() - 1; i2 >= 0; i2--) {
                Fragment f2 = (Fragment) this.mActive.get(i2);
                if (f2 != null && f2.mFragmentId == id) {
                    return f2;
                }
            }
        }
        return null;
    }

    public Fragment findFragmentByTag(String tag) {
        if (!(this.mAdded == null || tag == null)) {
            for (int i = this.mAdded.size() - 1; i >= 0; i--) {
                Fragment f = (Fragment) this.mAdded.get(i);
                if (f != null && tag.equals(f.mTag)) {
                    return f;
                }
            }
        }
        if (!(this.mActive == null || tag == null)) {
            for (int i2 = this.mActive.size() - 1; i2 >= 0; i2--) {
                Fragment f2 = (Fragment) this.mActive.get(i2);
                if (f2 != null && tag.equals(f2.mTag)) {
                    return f2;
                }
            }
        }
        return null;
    }

    public Fragment findFragmentByWho(String who) {
        if (!(this.mActive == null || who == null)) {
            for (int i = this.mActive.size() - 1; i >= 0; i--) {
                Fragment f = (Fragment) this.mActive.get(i);
                if (f != null) {
                    Fragment f2 = f.findFragmentByWho(who);
                    if (f2 != null) {
                        return f2;
                    }
                }
            }
        }
        return null;
    }

    private void checkStateLoss() {
        if (this.mStateSaved) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        } else if (this.mNoTransactionsBecause != null) {
            throw new IllegalStateException("Can not perform this action inside of " + this.mNoTransactionsBecause);
        }
    }

    public void enqueueAction(OpGenerator action, boolean allowStateLoss) {
        if (!allowStateLoss) {
            checkStateLoss();
        }
        synchronized (this) {
            if (this.mDestroyed || this.mHost == null) {
                throw new IllegalStateException("Activity has been destroyed");
            }
            if (this.mPendingActions == null) {
                this.mPendingActions = new ArrayList<>();
            }
            this.mPendingActions.add(action);
            scheduleCommit();
        }
    }

    /* access modifiers changed from: private */
    public void scheduleCommit() {
        boolean postponeReady;
        boolean pendingReady = true;
        synchronized (this) {
            if (this.mPostponedTransactions == null || this.mPostponedTransactions.isEmpty()) {
                postponeReady = false;
            } else {
                postponeReady = true;
            }
            if (this.mPendingActions == null || this.mPendingActions.size() != 1) {
                pendingReady = false;
            }
            if (postponeReady || pendingReady) {
                this.mHost.getHandler().removeCallbacks(this.mExecCommit);
                this.mHost.getHandler().post(this.mExecCommit);
            }
        }
    }

    public int allocBackStackIndex(BackStackRecord bse) {
        synchronized (this) {
            if (this.mAvailBackStackIndices == null || this.mAvailBackStackIndices.size() <= 0) {
                if (this.mBackStackIndices == null) {
                    this.mBackStackIndices = new ArrayList<>();
                }
                int index = this.mBackStackIndices.size();
                if (DEBUG) {
                    Log.v(TAG, "Setting back stack index " + index + " to " + bse);
                }
                this.mBackStackIndices.add(bse);
                return index;
            }
            int index2 = ((Integer) this.mAvailBackStackIndices.remove(this.mAvailBackStackIndices.size() - 1)).intValue();
            if (DEBUG) {
                Log.v(TAG, "Adding back stack index " + index2 + " with " + bse);
            }
            this.mBackStackIndices.set(index2, bse);
            return index2;
        }
    }

    public void setBackStackIndex(int index, BackStackRecord bse) {
        synchronized (this) {
            if (this.mBackStackIndices == null) {
                this.mBackStackIndices = new ArrayList<>();
            }
            int N = this.mBackStackIndices.size();
            if (index < N) {
                if (DEBUG) {
                    Log.v(TAG, "Setting back stack index " + index + " to " + bse);
                }
                this.mBackStackIndices.set(index, bse);
            } else {
                while (N < index) {
                    this.mBackStackIndices.add(null);
                    if (this.mAvailBackStackIndices == null) {
                        this.mAvailBackStackIndices = new ArrayList<>();
                    }
                    if (DEBUG) {
                        Log.v(TAG, "Adding available back stack index " + N);
                    }
                    this.mAvailBackStackIndices.add(Integer.valueOf(N));
                    N++;
                }
                if (DEBUG) {
                    Log.v(TAG, "Adding back stack index " + index + " with " + bse);
                }
                this.mBackStackIndices.add(bse);
            }
        }
    }

    public void freeBackStackIndex(int index) {
        synchronized (this) {
            this.mBackStackIndices.set(index, null);
            if (this.mAvailBackStackIndices == null) {
                this.mAvailBackStackIndices = new ArrayList<>();
            }
            if (DEBUG) {
                Log.v(TAG, "Freeing back stack index " + index);
            }
            this.mAvailBackStackIndices.add(Integer.valueOf(index));
        }
    }

    private void ensureExecReady(boolean allowStateLoss) {
        if (this.mExecutingActions) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        } else if (Looper.myLooper() != this.mHost.getHandler().getLooper()) {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        } else {
            if (!allowStateLoss) {
                checkStateLoss();
            }
            if (this.mTmpRecords == null) {
                this.mTmpRecords = new ArrayList<>();
                this.mTmpIsPop = new ArrayList<>();
            }
            executePostponedTransaction(null, null);
        }
    }

    public void execSingleAction(OpGenerator action, boolean allowStateLoss) {
        ensureExecReady(allowStateLoss);
        if (action.generateOps(this.mTmpRecords, this.mTmpIsPop)) {
            this.mExecutingActions = true;
            try {
                optimizeAndExecuteOps(this.mTmpRecords, this.mTmpIsPop);
            } finally {
                cleanupExec();
            }
        }
        doPendingDeferredStart();
    }

    private void cleanupExec() {
        this.mExecutingActions = false;
        this.mTmpIsPop.clear();
        this.mTmpRecords.clear();
    }

    /* JADX INFO: finally extract failed */
    public boolean execPendingActions() {
        ensureExecReady(true);
        boolean didSomething = false;
        while (generateOpsForPendingActions(this.mTmpRecords, this.mTmpIsPop)) {
            this.mExecutingActions = true;
            try {
                optimizeAndExecuteOps(this.mTmpRecords, this.mTmpIsPop);
                cleanupExec();
                didSomething = true;
            } catch (Throwable th) {
                cleanupExec();
                throw th;
            }
        }
        doPendingDeferredStart();
        return didSomething;
    }

    private void executePostponedTransaction(ArrayList<BackStackRecord> records, ArrayList<Boolean> isRecordPop) {
        int numPostponed = this.mPostponedTransactions == null ? 0 : this.mPostponedTransactions.size();
        int i = 0;
        while (i < numPostponed) {
            StartEnterTransitionListener listener = (StartEnterTransitionListener) this.mPostponedTransactions.get(i);
            if (records != null && !listener.mIsBack) {
                int index = records.indexOf(listener.mRecord);
                if (index != -1 && ((Boolean) isRecordPop.get(index)).booleanValue()) {
                    listener.cancelTransaction();
                    i++;
                }
            }
            if (listener.isReady() || (records != null && listener.mRecord.interactsWith(records, 0, records.size()))) {
                this.mPostponedTransactions.remove(i);
                i--;
                numPostponed--;
                if (records != null && !listener.mIsBack) {
                    int index2 = records.indexOf(listener.mRecord);
                    if (index2 != -1 && ((Boolean) isRecordPop.get(index2)).booleanValue()) {
                        listener.cancelTransaction();
                    }
                }
                listener.completeTransaction();
            }
            i++;
        }
    }

    private void optimizeAndExecuteOps(ArrayList<BackStackRecord> records, ArrayList<Boolean> isRecordPop) {
        if (records != null && !records.isEmpty()) {
            if (isRecordPop == null || records.size() != isRecordPop.size()) {
                throw new IllegalStateException("Internal error with the back stack records");
            }
            executePostponedTransaction(records, isRecordPop);
            int numRecords = records.size();
            int startIndex = 0;
            int recordNum = 0;
            while (recordNum < numRecords) {
                if (!((BackStackRecord) records.get(recordNum)).mAllowOptimization) {
                    if (startIndex != recordNum) {
                        executeOpsTogether(records, isRecordPop, startIndex, recordNum);
                    }
                    int optimizeEnd = recordNum + 1;
                    if (((Boolean) isRecordPop.get(recordNum)).booleanValue()) {
                        while (optimizeEnd < numRecords && ((Boolean) isRecordPop.get(optimizeEnd)).booleanValue() && !((BackStackRecord) records.get(optimizeEnd)).mAllowOptimization) {
                            optimizeEnd++;
                        }
                    }
                    executeOpsTogether(records, isRecordPop, recordNum, optimizeEnd);
                    startIndex = optimizeEnd;
                    recordNum = optimizeEnd - 1;
                }
                recordNum++;
            }
            if (startIndex != numRecords) {
                executeOpsTogether(records, isRecordPop, startIndex, numRecords);
            }
        }
    }

    private void executeOpsTogether(ArrayList<BackStackRecord> records, ArrayList<Boolean> isRecordPop, int startIndex, int endIndex) {
        boolean allowOptimization = ((BackStackRecord) records.get(startIndex)).mAllowOptimization;
        boolean addToBackStack = false;
        if (this.mTmpAddedFragments == null) {
            this.mTmpAddedFragments = new ArrayList<>();
        } else {
            this.mTmpAddedFragments.clear();
        }
        if (this.mAdded != null) {
            this.mTmpAddedFragments.addAll(this.mAdded);
        }
        for (int recordNum = startIndex; recordNum < endIndex; recordNum++) {
            BackStackRecord record = (BackStackRecord) records.get(recordNum);
            boolean isPop = ((Boolean) isRecordPop.get(recordNum)).booleanValue();
            if (!isPop) {
                record.expandReplaceOps(this.mTmpAddedFragments);
            } else {
                record.trackAddedFragmentsInPop(this.mTmpAddedFragments);
            }
            record.bumpBackStackNesting(isPop ? -1 : 1);
            if (addToBackStack || record.mAddToBackStack) {
                addToBackStack = true;
            } else {
                addToBackStack = false;
            }
        }
        this.mTmpAddedFragments.clear();
        if (!allowOptimization) {
            FragmentTransition.startTransitions(this, records, isRecordPop, startIndex, endIndex, false);
        }
        executeOps(records, isRecordPop, startIndex, endIndex);
        int postponeIndex = endIndex;
        if (allowOptimization) {
            ArraySet<Fragment> addedFragments = new ArraySet<>();
            addAddedFragments(addedFragments);
            postponeIndex = postponePostponableTransactions(records, isRecordPop, startIndex, endIndex, addedFragments);
            makeRemovedFragmentsInvisible(addedFragments);
        }
        if (postponeIndex != startIndex && allowOptimization) {
            FragmentTransition.startTransitions(this, records, isRecordPop, startIndex, postponeIndex, true);
            moveToState(this.mCurState, true);
        }
        for (int recordNum2 = startIndex; recordNum2 < endIndex; recordNum2++) {
            BackStackRecord record2 = (BackStackRecord) records.get(recordNum2);
            if (((Boolean) isRecordPop.get(recordNum2)).booleanValue() && record2.mIndex >= 0) {
                freeBackStackIndex(record2.mIndex);
                record2.mIndex = -1;
            }
        }
        if (addToBackStack) {
            reportBackStackChanged();
        }
    }

    private void makeRemovedFragmentsInvisible(ArraySet<Fragment> fragments) {
        int numAdded = fragments.size();
        for (int i = 0; i < numAdded; i++) {
            Fragment fragment = (Fragment) fragments.valueAt(i);
            if (!fragment.mAdded) {
                View view = fragment.getView();
                if (VERSION.SDK_INT < 11) {
                    fragment.getView().setVisibility(4);
                } else {
                    fragment.mPostponedAlpha = view.getAlpha();
                    view.setAlpha(0.0f);
                }
            }
        }
    }

    private int postponePostponableTransactions(ArrayList<BackStackRecord> records, ArrayList<Boolean> isRecordPop, int startIndex, int endIndex, ArraySet<Fragment> added) {
        int postponeIndex = endIndex;
        for (int i = endIndex - 1; i >= startIndex; i--) {
            BackStackRecord record = (BackStackRecord) records.get(i);
            boolean isPop = ((Boolean) isRecordPop.get(i)).booleanValue();
            if (record.isPostponed() && !record.interactsWith(records, i + 1, endIndex)) {
                if (this.mPostponedTransactions == null) {
                    this.mPostponedTransactions = new ArrayList<>();
                }
                StartEnterTransitionListener listener = new StartEnterTransitionListener(record, isPop);
                this.mPostponedTransactions.add(listener);
                record.setOnStartPostponedListener(listener);
                if (isPop) {
                    record.executeOps();
                } else {
                    record.executePopOps();
                }
                postponeIndex--;
                if (i != postponeIndex) {
                    records.remove(i);
                    records.add(postponeIndex, record);
                }
                addAddedFragments(added);
            }
        }
        return postponeIndex;
    }

    /* access modifiers changed from: private */
    public void completeExecute(BackStackRecord record, boolean isPop, boolean runTransitions, boolean moveToState) {
        ArrayList<BackStackRecord> records = new ArrayList<>(1);
        ArrayList<Boolean> isRecordPop = new ArrayList<>(1);
        records.add(record);
        isRecordPop.add(Boolean.valueOf(isPop));
        executeOps(records, isRecordPop, 0, 1);
        if (runTransitions) {
            FragmentTransition.startTransitions(this, records, isRecordPop, 0, 1, true);
        }
        if (moveToState) {
            moveToState(this.mCurState, true);
        }
        if (this.mActive != null) {
            int numActive = this.mActive.size();
            for (int i = 0; i < numActive; i++) {
                Fragment fragment = (Fragment) this.mActive.get(i);
                if (fragment != null && fragment.mView != null && fragment.mIsNewlyAdded && record.interactsWith(fragment.mContainerId)) {
                    if (VERSION.SDK_INT >= 11 && fragment.mPostponedAlpha > 0.0f) {
                        fragment.mView.setAlpha(fragment.mPostponedAlpha);
                    }
                    if (moveToState) {
                        fragment.mPostponedAlpha = 0.0f;
                    } else {
                        fragment.mPostponedAlpha = -1.0f;
                        fragment.mIsNewlyAdded = false;
                    }
                }
            }
        }
    }

    private Fragment findFragmentUnder(Fragment f) {
        ViewGroup container = f.mContainer;
        View view = f.mView;
        if (container == null || view == null) {
            return null;
        }
        for (int i = this.mAdded.indexOf(f) - 1; i >= 0; i--) {
            Fragment underFragment = (Fragment) this.mAdded.get(i);
            if (underFragment.mContainer == container && underFragment.mView != null) {
                return underFragment;
            }
        }
        return null;
    }

    private static void executeOps(ArrayList<BackStackRecord> records, ArrayList<Boolean> isRecordPop, int startIndex, int endIndex) {
        for (int i = startIndex; i < endIndex; i++) {
            BackStackRecord record = (BackStackRecord) records.get(i);
            if (((Boolean) isRecordPop.get(i)).booleanValue()) {
                record.executePopOps();
            } else {
                record.executeOps();
            }
        }
    }

    private void addAddedFragments(ArraySet<Fragment> added) {
        if (this.mCurState >= 1) {
            int state = Math.min(this.mCurState, 4);
            int numAdded = this.mAdded == null ? 0 : this.mAdded.size();
            for (int i = 0; i < numAdded; i++) {
                Fragment fragment = (Fragment) this.mAdded.get(i);
                if (fragment.mState < state) {
                    moveToState(fragment, state, fragment.getNextAnim(), fragment.getNextTransition(), false);
                    if (fragment.mView != null && !fragment.mHidden && fragment.mIsNewlyAdded) {
                        added.add(fragment);
                    }
                }
            }
        }
    }

    private void forcePostponedTransactions() {
        if (this.mPostponedTransactions != null) {
            while (!this.mPostponedTransactions.isEmpty()) {
                ((StartEnterTransitionListener) this.mPostponedTransactions.remove(0)).completeTransaction();
            }
        }
    }

    private void endAnimatingAwayFragments() {
        int numFragments;
        if (this.mActive == null) {
            numFragments = 0;
        } else {
            numFragments = this.mActive.size();
        }
        for (int i = 0; i < numFragments; i++) {
            Fragment fragment = (Fragment) this.mActive.get(i);
            if (!(fragment == null || fragment.getAnimatingAway() == null)) {
                int stateAfterAnimating = fragment.getStateAfterAnimating();
                View animatingAway = fragment.getAnimatingAway();
                fragment.setAnimatingAway(null);
                Animation animation = animatingAway.getAnimation();
                if (animation != null) {
                    animation.cancel();
                }
                moveToState(fragment, stateAfterAnimating, 0, 0, false);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0039, code lost:
        if (r1 <= 0) goto L_0x0040;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean generateOpsForPendingActions(java.util.ArrayList<android.support.p000v4.app.BackStackRecord> r6, java.util.ArrayList<java.lang.Boolean> r7) {
        /*
            r5 = this;
            r3 = 0
            monitor-enter(r5)
            java.util.ArrayList<android.support.v4.app.FragmentManagerImpl$OpGenerator> r2 = r5.mPendingActions     // Catch:{ all -> 0x003d }
            if (r2 == 0) goto L_0x000e
            java.util.ArrayList<android.support.v4.app.FragmentManagerImpl$OpGenerator> r2 = r5.mPendingActions     // Catch:{ all -> 0x003d }
            int r2 = r2.size()     // Catch:{ all -> 0x003d }
            if (r2 != 0) goto L_0x0011
        L_0x000e:
            monitor-exit(r5)     // Catch:{ all -> 0x003d }
            r2 = r3
        L_0x0010:
            return r2
        L_0x0011:
            java.util.ArrayList<android.support.v4.app.FragmentManagerImpl$OpGenerator> r2 = r5.mPendingActions     // Catch:{ all -> 0x003d }
            int r1 = r2.size()     // Catch:{ all -> 0x003d }
            r0 = 0
        L_0x0018:
            if (r0 >= r1) goto L_0x0028
            java.util.ArrayList<android.support.v4.app.FragmentManagerImpl$OpGenerator> r2 = r5.mPendingActions     // Catch:{ all -> 0x003d }
            java.lang.Object r2 = r2.get(r0)     // Catch:{ all -> 0x003d }
            android.support.v4.app.FragmentManagerImpl$OpGenerator r2 = (android.support.p000v4.app.FragmentManagerImpl.OpGenerator) r2     // Catch:{ all -> 0x003d }
            r2.generateOps(r6, r7)     // Catch:{ all -> 0x003d }
            int r0 = r0 + 1
            goto L_0x0018
        L_0x0028:
            java.util.ArrayList<android.support.v4.app.FragmentManagerImpl$OpGenerator> r2 = r5.mPendingActions     // Catch:{ all -> 0x003d }
            r2.clear()     // Catch:{ all -> 0x003d }
            android.support.v4.app.FragmentHostCallback r2 = r5.mHost     // Catch:{ all -> 0x003d }
            android.os.Handler r2 = r2.getHandler()     // Catch:{ all -> 0x003d }
            java.lang.Runnable r4 = r5.mExecCommit     // Catch:{ all -> 0x003d }
            r2.removeCallbacks(r4)     // Catch:{ all -> 0x003d }
            monitor-exit(r5)     // Catch:{ all -> 0x003d }
            if (r1 <= 0) goto L_0x0040
            r2 = 1
            goto L_0x0010
        L_0x003d:
            r2 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x003d }
            throw r2
        L_0x0040:
            r2 = r3
            goto L_0x0010
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.app.FragmentManagerImpl.generateOpsForPendingActions(java.util.ArrayList, java.util.ArrayList):boolean");
    }

    /* access modifiers changed from: 0000 */
    public void doPendingDeferredStart() {
        if (this.mHavePendingDeferredStart) {
            boolean loadersRunning = false;
            for (int i = 0; i < this.mActive.size(); i++) {
                Fragment f = (Fragment) this.mActive.get(i);
                if (!(f == null || f.mLoaderManager == null)) {
                    loadersRunning |= f.mLoaderManager.hasRunningLoaders();
                }
            }
            if (!loadersRunning) {
                this.mHavePendingDeferredStart = false;
                startPendingDeferredFragments();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void reportBackStackChanged() {
        if (this.mBackStackChangeListeners != null) {
            for (int i = 0; i < this.mBackStackChangeListeners.size(); i++) {
                ((OnBackStackChangedListener) this.mBackStackChangeListeners.get(i)).onBackStackChanged();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void addBackStackState(BackStackRecord state) {
        if (this.mBackStack == null) {
            this.mBackStack = new ArrayList<>();
        }
        this.mBackStack.add(state);
        reportBackStackChanged();
    }

    /* access modifiers changed from: 0000 */
    public boolean popBackStackState(ArrayList<BackStackRecord> records, ArrayList<Boolean> isRecordPop, String name, int id, int flags) {
        if (this.mBackStack == null) {
            return false;
        }
        if (name == null && id < 0 && (flags & 1) == 0) {
            int last = this.mBackStack.size() - 1;
            if (last < 0) {
                return false;
            }
            records.add(this.mBackStack.remove(last));
            isRecordPop.add(Boolean.valueOf(true));
        } else {
            int index = -1;
            if (name != null || id >= 0) {
                int index2 = this.mBackStack.size() - 1;
                while (index >= 0) {
                    BackStackRecord bss = (BackStackRecord) this.mBackStack.get(index);
                    if ((name != null && name.equals(bss.getName())) || (id >= 0 && id == bss.mIndex)) {
                        break;
                    }
                    index2 = index - 1;
                }
                if (index < 0) {
                    return false;
                }
                if ((flags & 1) != 0) {
                    index--;
                    while (index >= 0) {
                        BackStackRecord bss2 = (BackStackRecord) this.mBackStack.get(index);
                        if ((name == null || !name.equals(bss2.getName())) && (id < 0 || id != bss2.mIndex)) {
                            break;
                        }
                        index--;
                    }
                }
            }
            if (index == this.mBackStack.size() - 1) {
                return false;
            }
            for (int i = this.mBackStack.size() - 1; i > index; i--) {
                records.add(this.mBackStack.remove(i));
                isRecordPop.add(Boolean.valueOf(true));
            }
        }
        return true;
    }

    /* access modifiers changed from: 0000 */
    public FragmentManagerNonConfig retainNonConfig() {
        ArrayList<Fragment> fragments = null;
        ArrayList<FragmentManagerNonConfig> childFragments = null;
        if (this.mActive != null) {
            for (int i = 0; i < this.mActive.size(); i++) {
                Fragment f = (Fragment) this.mActive.get(i);
                if (f != null) {
                    if (f.mRetainInstance) {
                        if (fragments == null) {
                            fragments = new ArrayList<>();
                        }
                        fragments.add(f);
                        f.mRetaining = true;
                        f.mTargetIndex = f.mTarget != null ? f.mTarget.mIndex : -1;
                        if (DEBUG) {
                            Log.v(TAG, "retainNonConfig: keeping retained " + f);
                        }
                    }
                    boolean addedChild = false;
                    if (f.mChildFragmentManager != null) {
                        FragmentManagerNonConfig child = f.mChildFragmentManager.retainNonConfig();
                        if (child != null) {
                            if (childFragments == null) {
                                childFragments = new ArrayList<>();
                                for (int j = 0; j < i; j++) {
                                    childFragments.add(null);
                                }
                            }
                            childFragments.add(child);
                            addedChild = true;
                        }
                    }
                    if (childFragments != null && !addedChild) {
                        childFragments.add(null);
                    }
                }
            }
        }
        if (fragments == null && childFragments == null) {
            return null;
        }
        return new FragmentManagerNonConfig(fragments, childFragments);
    }

    /* access modifiers changed from: 0000 */
    public void saveFragmentViewState(Fragment f) {
        if (f.mInnerView != null) {
            if (this.mStateArray == null) {
                this.mStateArray = new SparseArray<>();
            } else {
                this.mStateArray.clear();
            }
            f.mInnerView.saveHierarchyState(this.mStateArray);
            if (this.mStateArray.size() > 0) {
                f.mSavedViewState = this.mStateArray;
                this.mStateArray = null;
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public Bundle saveFragmentBasicState(Fragment f) {
        Bundle result = null;
        if (this.mStateBundle == null) {
            this.mStateBundle = new Bundle();
        }
        f.performSaveInstanceState(this.mStateBundle);
        dispatchOnFragmentSaveInstanceState(f, this.mStateBundle, false);
        if (!this.mStateBundle.isEmpty()) {
            result = this.mStateBundle;
            this.mStateBundle = null;
        }
        if (f.mView != null) {
            saveFragmentViewState(f);
        }
        if (f.mSavedViewState != null) {
            if (result == null) {
                result = new Bundle();
            }
            result.putSparseParcelableArray(VIEW_STATE_TAG, f.mSavedViewState);
        }
        if (!f.mUserVisibleHint) {
            if (result == null) {
                result = new Bundle();
            }
            result.putBoolean(USER_VISIBLE_HINT_TAG, f.mUserVisibleHint);
        }
        return result;
    }

    /* access modifiers changed from: 0000 */
    public Parcelable saveAllState() {
        forcePostponedTransactions();
        endAnimatingAwayFragments();
        execPendingActions();
        if (HONEYCOMB) {
            this.mStateSaved = true;
        }
        if (this.mActive == null || this.mActive.size() <= 0) {
            return null;
        }
        int N = this.mActive.size();
        FragmentState[] active = new FragmentState[N];
        boolean haveFragments = false;
        for (int i = 0; i < N; i++) {
            Fragment f = (Fragment) this.mActive.get(i);
            if (f != null) {
                if (f.mIndex < 0) {
                    throwException(new IllegalStateException("Failure saving state: active " + f + " has cleared index: " + f.mIndex));
                }
                haveFragments = true;
                FragmentState fs = new FragmentState(f);
                active[i] = fs;
                if (f.mState <= 0 || fs.mSavedFragmentState != null) {
                    fs.mSavedFragmentState = f.mSavedFragmentState;
                } else {
                    fs.mSavedFragmentState = saveFragmentBasicState(f);
                    if (f.mTarget != null) {
                        if (f.mTarget.mIndex < 0) {
                            throwException(new IllegalStateException("Failure saving state: " + f + " has target not in fragment manager: " + f.mTarget));
                        }
                        if (fs.mSavedFragmentState == null) {
                            fs.mSavedFragmentState = new Bundle();
                        }
                        putFragment(fs.mSavedFragmentState, TARGET_STATE_TAG, f.mTarget);
                        if (f.mTargetRequestCode != 0) {
                            fs.mSavedFragmentState.putInt(TARGET_REQUEST_CODE_STATE_TAG, f.mTargetRequestCode);
                        }
                    }
                }
                if (DEBUG) {
                    Log.v(TAG, "Saved state of " + f + ": " + fs.mSavedFragmentState);
                }
            }
        }
        if (haveFragments) {
            int[] added = null;
            BackStackState[] backStack = null;
            if (this.mAdded != null) {
                int N2 = this.mAdded.size();
                if (N2 > 0) {
                    added = new int[N2];
                    for (int i2 = 0; i2 < N2; i2++) {
                        added[i2] = ((Fragment) this.mAdded.get(i2)).mIndex;
                        if (added[i2] < 0) {
                            throwException(new IllegalStateException("Failure saving state: active " + this.mAdded.get(i2) + " has cleared index: " + added[i2]));
                        }
                        if (DEBUG) {
                            Log.v(TAG, "saveAllState: adding fragment #" + i2 + ": " + this.mAdded.get(i2));
                        }
                    }
                }
            }
            if (this.mBackStack != null) {
                int N3 = this.mBackStack.size();
                if (N3 > 0) {
                    backStack = new BackStackState[N3];
                    for (int i3 = 0; i3 < N3; i3++) {
                        backStack[i3] = new BackStackState((BackStackRecord) this.mBackStack.get(i3));
                        if (DEBUG) {
                            Log.v(TAG, "saveAllState: adding back stack #" + i3 + ": " + this.mBackStack.get(i3));
                        }
                    }
                }
            }
            FragmentManagerState fms = new FragmentManagerState();
            fms.mActive = active;
            fms.mAdded = added;
            fms.mBackStack = backStack;
            return fms;
        } else if (!DEBUG) {
            return null;
        } else {
            Log.v(TAG, "saveAllState: no fragments!");
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    public void restoreAllState(Parcelable state, FragmentManagerNonConfig nonConfig) {
        if (state != null) {
            FragmentManagerState fms = (FragmentManagerState) state;
            if (fms.mActive != null) {
                List<FragmentManagerNonConfig> childNonConfigs = null;
                if (nonConfig != null) {
                    List<Fragment> nonConfigFragments = nonConfig.getFragments();
                    childNonConfigs = nonConfig.getChildNonConfigs();
                    int count = nonConfigFragments != null ? nonConfigFragments.size() : 0;
                    for (int i = 0; i < count; i++) {
                        Fragment f = (Fragment) nonConfigFragments.get(i);
                        if (DEBUG) {
                            Log.v(TAG, "restoreAllState: re-attaching retained " + f);
                        }
                        FragmentState fs = fms.mActive[f.mIndex];
                        fs.mInstance = f;
                        f.mSavedViewState = null;
                        f.mBackStackNesting = 0;
                        f.mInLayout = false;
                        f.mAdded = false;
                        f.mTarget = null;
                        if (fs.mSavedFragmentState != null) {
                            fs.mSavedFragmentState.setClassLoader(this.mHost.getContext().getClassLoader());
                            f.mSavedViewState = fs.mSavedFragmentState.getSparseParcelableArray(VIEW_STATE_TAG);
                            f.mSavedFragmentState = fs.mSavedFragmentState;
                        }
                    }
                }
                this.mActive = new ArrayList<>(fms.mActive.length);
                if (this.mAvailIndices != null) {
                    this.mAvailIndices.clear();
                }
                for (int i2 = 0; i2 < fms.mActive.length; i2++) {
                    FragmentState fs2 = fms.mActive[i2];
                    if (fs2 != null) {
                        FragmentManagerNonConfig childNonConfig = null;
                        if (childNonConfigs != null && i2 < childNonConfigs.size()) {
                            childNonConfig = (FragmentManagerNonConfig) childNonConfigs.get(i2);
                        }
                        Fragment f2 = fs2.instantiate(this.mHost, this.mParent, childNonConfig);
                        if (DEBUG) {
                            Log.v(TAG, "restoreAllState: active #" + i2 + ": " + f2);
                        }
                        this.mActive.add(f2);
                        fs2.mInstance = null;
                    } else {
                        this.mActive.add(null);
                        if (this.mAvailIndices == null) {
                            this.mAvailIndices = new ArrayList<>();
                        }
                        if (DEBUG) {
                            Log.v(TAG, "restoreAllState: avail #" + i2);
                        }
                        this.mAvailIndices.add(Integer.valueOf(i2));
                    }
                }
                if (nonConfig != null) {
                    List<Fragment> nonConfigFragments2 = nonConfig.getFragments();
                    int count2 = nonConfigFragments2 != null ? nonConfigFragments2.size() : 0;
                    for (int i3 = 0; i3 < count2; i3++) {
                        Fragment f3 = (Fragment) nonConfigFragments2.get(i3);
                        if (f3.mTargetIndex >= 0) {
                            if (f3.mTargetIndex < this.mActive.size()) {
                                f3.mTarget = (Fragment) this.mActive.get(f3.mTargetIndex);
                            } else {
                                Log.w(TAG, "Re-attaching retained fragment " + f3 + " target no longer exists: " + f3.mTargetIndex);
                                f3.mTarget = null;
                            }
                        }
                    }
                }
                if (fms.mAdded != null) {
                    this.mAdded = new ArrayList<>(fms.mAdded.length);
                    for (int i4 = 0; i4 < fms.mAdded.length; i4++) {
                        Fragment f4 = (Fragment) this.mActive.get(fms.mAdded[i4]);
                        if (f4 == null) {
                            throwException(new IllegalStateException("No instantiated fragment for index #" + fms.mAdded[i4]));
                        }
                        f4.mAdded = true;
                        if (DEBUG) {
                            Log.v(TAG, "restoreAllState: added #" + i4 + ": " + f4);
                        }
                        if (this.mAdded.contains(f4)) {
                            throw new IllegalStateException("Already added!");
                        }
                        this.mAdded.add(f4);
                    }
                } else {
                    this.mAdded = null;
                }
                if (fms.mBackStack != null) {
                    this.mBackStack = new ArrayList<>(fms.mBackStack.length);
                    for (int i5 = 0; i5 < fms.mBackStack.length; i5++) {
                        BackStackRecord bse = fms.mBackStack[i5].instantiate(this);
                        if (DEBUG) {
                            Log.v(TAG, "restoreAllState: back stack #" + i5 + " (index " + bse.mIndex + "): " + bse);
                            bse.dump("  ", new PrintWriter(new LogWriter(TAG)), false);
                        }
                        this.mBackStack.add(bse);
                        if (bse.mIndex >= 0) {
                            setBackStackIndex(bse.mIndex, bse);
                        }
                    }
                    return;
                }
                this.mBackStack = null;
            }
        }
    }

    public void attachController(FragmentHostCallback host, FragmentContainer container, Fragment parent) {
        if (this.mHost != null) {
            throw new IllegalStateException("Already attached");
        }
        this.mHost = host;
        this.mContainer = container;
        this.mParent = parent;
    }

    public void noteStateNotSaved() {
        this.mStateSaved = false;
    }

    public void dispatchCreate() {
        this.mStateSaved = false;
        moveToState(1, false);
    }

    public void dispatchActivityCreated() {
        this.mStateSaved = false;
        moveToState(2, false);
    }

    public void dispatchStart() {
        this.mStateSaved = false;
        moveToState(4, false);
    }

    public void dispatchResume() {
        this.mStateSaved = false;
        moveToState(5, false);
    }

    public void dispatchPause() {
        moveToState(4, false);
    }

    public void dispatchStop() {
        this.mStateSaved = true;
        moveToState(3, false);
    }

    public void dispatchReallyStop() {
        moveToState(2, false);
    }

    public void dispatchDestroyView() {
        moveToState(1, false);
    }

    public void dispatchDestroy() {
        this.mDestroyed = true;
        execPendingActions();
        moveToState(0, false);
        this.mHost = null;
        this.mContainer = null;
        this.mParent = null;
    }

    public void dispatchMultiWindowModeChanged(boolean isInMultiWindowMode) {
        if (this.mAdded != null) {
            for (int i = this.mAdded.size() - 1; i >= 0; i--) {
                Fragment f = (Fragment) this.mAdded.get(i);
                if (f != null) {
                    f.performMultiWindowModeChanged(isInMultiWindowMode);
                }
            }
        }
    }

    public void dispatchPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
        if (this.mAdded != null) {
            for (int i = this.mAdded.size() - 1; i >= 0; i--) {
                Fragment f = (Fragment) this.mAdded.get(i);
                if (f != null) {
                    f.performPictureInPictureModeChanged(isInPictureInPictureMode);
                }
            }
        }
    }

    public void dispatchConfigurationChanged(Configuration newConfig) {
        if (this.mAdded != null) {
            for (int i = 0; i < this.mAdded.size(); i++) {
                Fragment f = (Fragment) this.mAdded.get(i);
                if (f != null) {
                    f.performConfigurationChanged(newConfig);
                }
            }
        }
    }

    public void dispatchLowMemory() {
        if (this.mAdded != null) {
            for (int i = 0; i < this.mAdded.size(); i++) {
                Fragment f = (Fragment) this.mAdded.get(i);
                if (f != null) {
                    f.performLowMemory();
                }
            }
        }
    }

    public boolean dispatchCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        boolean show = false;
        ArrayList<Fragment> newMenus = null;
        if (this.mAdded != null) {
            for (int i = 0; i < this.mAdded.size(); i++) {
                Fragment f = (Fragment) this.mAdded.get(i);
                if (f != null && f.performCreateOptionsMenu(menu, inflater)) {
                    show = true;
                    if (newMenus == null) {
                        newMenus = new ArrayList<>();
                    }
                    newMenus.add(f);
                }
            }
        }
        if (this.mCreatedMenus != null) {
            for (int i2 = 0; i2 < this.mCreatedMenus.size(); i2++) {
                Fragment f2 = (Fragment) this.mCreatedMenus.get(i2);
                if (newMenus == null || !newMenus.contains(f2)) {
                    f2.onDestroyOptionsMenu();
                }
            }
        }
        this.mCreatedMenus = newMenus;
        return show;
    }

    public boolean dispatchPrepareOptionsMenu(Menu menu) {
        boolean show = false;
        if (this.mAdded != null) {
            for (int i = 0; i < this.mAdded.size(); i++) {
                Fragment f = (Fragment) this.mAdded.get(i);
                if (f != null && f.performPrepareOptionsMenu(menu)) {
                    show = true;
                }
            }
        }
        return show;
    }

    public boolean dispatchOptionsItemSelected(MenuItem item) {
        if (this.mAdded != null) {
            for (int i = 0; i < this.mAdded.size(); i++) {
                Fragment f = (Fragment) this.mAdded.get(i);
                if (f != null && f.performOptionsItemSelected(item)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dispatchContextItemSelected(MenuItem item) {
        if (this.mAdded != null) {
            for (int i = 0; i < this.mAdded.size(); i++) {
                Fragment f = (Fragment) this.mAdded.get(i);
                if (f != null && f.performContextItemSelected(item)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void dispatchOptionsMenuClosed(Menu menu) {
        if (this.mAdded != null) {
            for (int i = 0; i < this.mAdded.size(); i++) {
                Fragment f = (Fragment) this.mAdded.get(i);
                if (f != null) {
                    f.performOptionsMenuClosed(menu);
                }
            }
        }
    }

    public void registerFragmentLifecycleCallbacks(FragmentLifecycleCallbacks cb, boolean recursive) {
        if (this.mLifecycleCallbacks == null) {
            this.mLifecycleCallbacks = new CopyOnWriteArrayList<>();
        }
        this.mLifecycleCallbacks.add(new Pair(cb, Boolean.valueOf(recursive)));
    }

    public void unregisterFragmentLifecycleCallbacks(FragmentLifecycleCallbacks cb) {
        if (this.mLifecycleCallbacks != null) {
            synchronized (this.mLifecycleCallbacks) {
                int i = 0;
                int N = this.mLifecycleCallbacks.size();
                while (true) {
                    if (i >= N) {
                        break;
                    } else if (((Pair) this.mLifecycleCallbacks.get(i)).first == cb) {
                        this.mLifecycleCallbacks.remove(i);
                        break;
                    } else {
                        i++;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void dispatchOnFragmentPreAttached(Fragment f, Context context, boolean onlyRecursive) {
        if (this.mParent != null) {
            FragmentManager parentManager = this.mParent.getFragmentManager();
            if (parentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) parentManager).dispatchOnFragmentPreAttached(f, context, true);
            }
        }
        if (this.mLifecycleCallbacks != null) {
            Iterator it = this.mLifecycleCallbacks.iterator();
            while (it.hasNext()) {
                Pair<FragmentLifecycleCallbacks, Boolean> p = (Pair) it.next();
                if (!onlyRecursive || ((Boolean) p.second).booleanValue()) {
                    ((FragmentLifecycleCallbacks) p.first).onFragmentPreAttached(this, f, context);
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void dispatchOnFragmentAttached(Fragment f, Context context, boolean onlyRecursive) {
        if (this.mParent != null) {
            FragmentManager parentManager = this.mParent.getFragmentManager();
            if (parentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) parentManager).dispatchOnFragmentAttached(f, context, true);
            }
        }
        if (this.mLifecycleCallbacks != null) {
            Iterator it = this.mLifecycleCallbacks.iterator();
            while (it.hasNext()) {
                Pair<FragmentLifecycleCallbacks, Boolean> p = (Pair) it.next();
                if (!onlyRecursive || ((Boolean) p.second).booleanValue()) {
                    ((FragmentLifecycleCallbacks) p.first).onFragmentAttached(this, f, context);
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void dispatchOnFragmentCreated(Fragment f, Bundle savedInstanceState, boolean onlyRecursive) {
        if (this.mParent != null) {
            FragmentManager parentManager = this.mParent.getFragmentManager();
            if (parentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) parentManager).dispatchOnFragmentCreated(f, savedInstanceState, true);
            }
        }
        if (this.mLifecycleCallbacks != null) {
            Iterator it = this.mLifecycleCallbacks.iterator();
            while (it.hasNext()) {
                Pair<FragmentLifecycleCallbacks, Boolean> p = (Pair) it.next();
                if (!onlyRecursive || ((Boolean) p.second).booleanValue()) {
                    ((FragmentLifecycleCallbacks) p.first).onFragmentCreated(this, f, savedInstanceState);
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void dispatchOnFragmentActivityCreated(Fragment f, Bundle savedInstanceState, boolean onlyRecursive) {
        if (this.mParent != null) {
            FragmentManager parentManager = this.mParent.getFragmentManager();
            if (parentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) parentManager).dispatchOnFragmentActivityCreated(f, savedInstanceState, true);
            }
        }
        if (this.mLifecycleCallbacks != null) {
            Iterator it = this.mLifecycleCallbacks.iterator();
            while (it.hasNext()) {
                Pair<FragmentLifecycleCallbacks, Boolean> p = (Pair) it.next();
                if (!onlyRecursive || ((Boolean) p.second).booleanValue()) {
                    ((FragmentLifecycleCallbacks) p.first).onFragmentActivityCreated(this, f, savedInstanceState);
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void dispatchOnFragmentViewCreated(Fragment f, View v, Bundle savedInstanceState, boolean onlyRecursive) {
        if (this.mParent != null) {
            FragmentManager parentManager = this.mParent.getFragmentManager();
            if (parentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) parentManager).dispatchOnFragmentViewCreated(f, v, savedInstanceState, true);
            }
        }
        if (this.mLifecycleCallbacks != null) {
            Iterator it = this.mLifecycleCallbacks.iterator();
            while (it.hasNext()) {
                Pair<FragmentLifecycleCallbacks, Boolean> p = (Pair) it.next();
                if (!onlyRecursive || ((Boolean) p.second).booleanValue()) {
                    ((FragmentLifecycleCallbacks) p.first).onFragmentViewCreated(this, f, v, savedInstanceState);
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void dispatchOnFragmentStarted(Fragment f, boolean onlyRecursive) {
        if (this.mParent != null) {
            FragmentManager parentManager = this.mParent.getFragmentManager();
            if (parentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) parentManager).dispatchOnFragmentStarted(f, true);
            }
        }
        if (this.mLifecycleCallbacks != null) {
            Iterator it = this.mLifecycleCallbacks.iterator();
            while (it.hasNext()) {
                Pair<FragmentLifecycleCallbacks, Boolean> p = (Pair) it.next();
                if (!onlyRecursive || ((Boolean) p.second).booleanValue()) {
                    ((FragmentLifecycleCallbacks) p.first).onFragmentStarted(this, f);
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void dispatchOnFragmentResumed(Fragment f, boolean onlyRecursive) {
        if (this.mParent != null) {
            FragmentManager parentManager = this.mParent.getFragmentManager();
            if (parentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) parentManager).dispatchOnFragmentResumed(f, true);
            }
        }
        if (this.mLifecycleCallbacks != null) {
            Iterator it = this.mLifecycleCallbacks.iterator();
            while (it.hasNext()) {
                Pair<FragmentLifecycleCallbacks, Boolean> p = (Pair) it.next();
                if (!onlyRecursive || ((Boolean) p.second).booleanValue()) {
                    ((FragmentLifecycleCallbacks) p.first).onFragmentResumed(this, f);
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void dispatchOnFragmentPaused(Fragment f, boolean onlyRecursive) {
        if (this.mParent != null) {
            FragmentManager parentManager = this.mParent.getFragmentManager();
            if (parentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) parentManager).dispatchOnFragmentPaused(f, true);
            }
        }
        if (this.mLifecycleCallbacks != null) {
            Iterator it = this.mLifecycleCallbacks.iterator();
            while (it.hasNext()) {
                Pair<FragmentLifecycleCallbacks, Boolean> p = (Pair) it.next();
                if (!onlyRecursive || ((Boolean) p.second).booleanValue()) {
                    ((FragmentLifecycleCallbacks) p.first).onFragmentPaused(this, f);
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void dispatchOnFragmentStopped(Fragment f, boolean onlyRecursive) {
        if (this.mParent != null) {
            FragmentManager parentManager = this.mParent.getFragmentManager();
            if (parentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) parentManager).dispatchOnFragmentStopped(f, true);
            }
        }
        if (this.mLifecycleCallbacks != null) {
            Iterator it = this.mLifecycleCallbacks.iterator();
            while (it.hasNext()) {
                Pair<FragmentLifecycleCallbacks, Boolean> p = (Pair) it.next();
                if (!onlyRecursive || ((Boolean) p.second).booleanValue()) {
                    ((FragmentLifecycleCallbacks) p.first).onFragmentStopped(this, f);
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void dispatchOnFragmentSaveInstanceState(Fragment f, Bundle outState, boolean onlyRecursive) {
        if (this.mParent != null) {
            FragmentManager parentManager = this.mParent.getFragmentManager();
            if (parentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) parentManager).dispatchOnFragmentSaveInstanceState(f, outState, true);
            }
        }
        if (this.mLifecycleCallbacks != null) {
            Iterator it = this.mLifecycleCallbacks.iterator();
            while (it.hasNext()) {
                Pair<FragmentLifecycleCallbacks, Boolean> p = (Pair) it.next();
                if (!onlyRecursive || ((Boolean) p.second).booleanValue()) {
                    ((FragmentLifecycleCallbacks) p.first).onFragmentSaveInstanceState(this, f, outState);
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void dispatchOnFragmentViewDestroyed(Fragment f, boolean onlyRecursive) {
        if (this.mParent != null) {
            FragmentManager parentManager = this.mParent.getFragmentManager();
            if (parentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) parentManager).dispatchOnFragmentViewDestroyed(f, true);
            }
        }
        if (this.mLifecycleCallbacks != null) {
            Iterator it = this.mLifecycleCallbacks.iterator();
            while (it.hasNext()) {
                Pair<FragmentLifecycleCallbacks, Boolean> p = (Pair) it.next();
                if (!onlyRecursive || ((Boolean) p.second).booleanValue()) {
                    ((FragmentLifecycleCallbacks) p.first).onFragmentViewDestroyed(this, f);
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void dispatchOnFragmentDestroyed(Fragment f, boolean onlyRecursive) {
        if (this.mParent != null) {
            FragmentManager parentManager = this.mParent.getFragmentManager();
            if (parentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) parentManager).dispatchOnFragmentDestroyed(f, true);
            }
        }
        if (this.mLifecycleCallbacks != null) {
            Iterator it = this.mLifecycleCallbacks.iterator();
            while (it.hasNext()) {
                Pair<FragmentLifecycleCallbacks, Boolean> p = (Pair) it.next();
                if (!onlyRecursive || ((Boolean) p.second).booleanValue()) {
                    ((FragmentLifecycleCallbacks) p.first).onFragmentDestroyed(this, f);
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void dispatchOnFragmentDetached(Fragment f, boolean onlyRecursive) {
        if (this.mParent != null) {
            FragmentManager parentManager = this.mParent.getFragmentManager();
            if (parentManager instanceof FragmentManagerImpl) {
                ((FragmentManagerImpl) parentManager).dispatchOnFragmentDetached(f, true);
            }
        }
        if (this.mLifecycleCallbacks != null) {
            Iterator it = this.mLifecycleCallbacks.iterator();
            while (it.hasNext()) {
                Pair<FragmentLifecycleCallbacks, Boolean> p = (Pair) it.next();
                if (!onlyRecursive || ((Boolean) p.second).booleanValue()) {
                    ((FragmentLifecycleCallbacks) p.first).onFragmentDetached(this, f);
                }
            }
        }
    }

    public static int reverseTransit(int transit) {
        switch (transit) {
            case FragmentTransaction.TRANSIT_FRAGMENT_OPEN /*4097*/:
                return 8194;
            case FragmentTransaction.TRANSIT_FRAGMENT_FADE /*4099*/:
                return FragmentTransaction.TRANSIT_FRAGMENT_FADE;
            case 8194:
                return FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
            default:
                return 0;
        }
    }

    public static int transitToStyleIndex(int transit, boolean enter) {
        switch (transit) {
            case FragmentTransaction.TRANSIT_FRAGMENT_OPEN /*4097*/:
                return enter ? 1 : 2;
            case FragmentTransaction.TRANSIT_FRAGMENT_FADE /*4099*/:
                return enter ? 5 : 6;
            case 8194:
                return enter ? 3 : 4;
            default:
                return -1;
        }
    }

    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        int containerId;
        Fragment fragment;
        int i;
        if (!"fragment".equals(name)) {
            return null;
        }
        String fname = attrs.getAttributeValue(null, "class");
        TypedArray a = context.obtainStyledAttributes(attrs, FragmentTag.Fragment);
        if (fname == null) {
            fname = a.getString(0);
        }
        int id = a.getResourceId(1, -1);
        String tag = a.getString(2);
        a.recycle();
        if (!Fragment.isSupportFragmentClass(this.mHost.getContext(), fname)) {
            return null;
        }
        if (parent != null) {
            containerId = parent.getId();
        } else {
            containerId = 0;
        }
        if (containerId == -1 && id == -1 && tag == null) {
            throw new IllegalArgumentException(attrs.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + fname);
        }
        if (id != -1) {
            fragment = findFragmentById(id);
        } else {
            fragment = null;
        }
        if (fragment == null && tag != null) {
            fragment = findFragmentByTag(tag);
        }
        if (fragment == null && containerId != -1) {
            fragment = findFragmentById(containerId);
        }
        if (DEBUG) {
            Log.v(TAG, "onCreateView: id=0x" + Integer.toHexString(id) + " fname=" + fname + " existing=" + fragment);
        }
        if (fragment == null) {
            fragment = Fragment.instantiate(context, fname);
            fragment.mFromLayout = true;
            if (id != 0) {
                i = id;
            } else {
                i = containerId;
            }
            fragment.mFragmentId = i;
            fragment.mContainerId = containerId;
            fragment.mTag = tag;
            fragment.mInLayout = true;
            fragment.mFragmentManager = this;
            fragment.mHost = this.mHost;
            fragment.onInflate(this.mHost.getContext(), attrs, fragment.mSavedFragmentState);
            addFragment(fragment, true);
        } else if (fragment.mInLayout) {
            throw new IllegalArgumentException(attrs.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(id) + ", tag " + tag + ", or parent id 0x" + Integer.toHexString(containerId) + " with another fragment for " + fname);
        } else {
            fragment.mInLayout = true;
            fragment.mHost = this.mHost;
            if (!fragment.mRetaining) {
                fragment.onInflate(this.mHost.getContext(), attrs, fragment.mSavedFragmentState);
            }
        }
        if (this.mCurState >= 1 || !fragment.mFromLayout) {
            moveToState(fragment);
        } else {
            moveToState(fragment, 1, 0, 0, false);
        }
        if (fragment.mView == null) {
            throw new IllegalStateException("Fragment " + fname + " did not create a view.");
        }
        if (id != 0) {
            fragment.mView.setId(id);
        }
        if (fragment.mView.getTag() == null) {
            fragment.mView.setTag(tag);
        }
        return fragment.mView;
    }

    /* access modifiers changed from: 0000 */
    public LayoutInflaterFactory getLayoutInflaterFactory() {
        return this;
    }
}
