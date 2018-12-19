package android.small.indeedfortunate.xqsmallnormal.activity.login;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.small.indeedfortunate.xqsmallnormal.App;
import android.small.indeedfortunate.xqsmallnormal.BaseActivity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SimpleTarget;

/**
 * Created by 旧城丷 on 2018/12/12.
 */

public class ImageLoader {
    /**
     * 清除硬盘缓存
     */
    public static void clearDiskCache() {
//        Glide.get(BaseApplication.getContext()).clearDiskCache();
    }

    /**
     * 清除内存缓存
     */
    public static void clearMemory() {
        Glide.get(App.getApplication()).clearMemory();
    }

    /**
     * 加载图片
     */
    // Glide 的一个优点是能够根据 Activity/Fragment 的生命周期加载图片，所以应该尽量不要使用 Application
    public static void loadImage(Object model, ImageView imageView) {
        loadImage(imageView.getContext(), model, imageView);
    }

    /**
     * 加载圆形图片
     */
    public static void loadCircleImage(Context context, Object model, ImageView imageView) {
//        Glide.with(context).load(model).crossFade().transform(new GlideCircleTransform(context)).into(imageView);
    }

    /**
     * 加载圆形图片
     */
    public static void loadCircleImage(Fragment context, Object model, ImageView imageView) {
//        Glide.with(context).load(model).crossFade().transform(new GlideCircleTransform(context.getContext())).into(imageView);
    }

    /**
     * 同步获取bitmap
     *
     * @param context
     * @param model
     * @return
     */
    public static Bitmap getBitmapFrom(Context context, Object model) {
        try {
            return null;
//            return Glide.with(context).load(model).asBitmap().into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过 using(cacheOnlyStreamLoader) 的确可以只获取缓存中的数据
     * 但是 Glide 又要求必须在非UI线程中调用 RequestFutureTarget#get()
     * 通过反射
     * Field field = futureTarget.getClass().getDeclaredField("assertBackgroundThread");
     * field.setAccessible(true);
     * field.set(futureTarget, false)
     * 可以实现在 UI 线程中调用 RequestFutureTarget#get()
     * 这样又会造成 ANR 的问题
     * 所以在目前情况下，仍然没有好的方法来同步获取 Glide 中的缓存数据
     *
     * @param context
     * @param model
     * @return
     */
    public static Bitmap getBitmapFromCache(Context context, String model) {
//        try {
//            return Glide.with(context)
//                    .using(cacheOnlyStreamLoader)
//                    .load(model)
//                    .asBitmap()
//                    .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).get();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return null;
    }

//    private static final StreamModelLoader<String> cacheOnlyStreamLoader = new StreamModelLoader<String>() {
//        @Override
//        public DataFetcher<InputStream> getResourceFetcher(final String model, int i, int i1) {
//            return new DataFetcher<InputStream>() {
//                @Override
//                public InputStream loadData(Priority priority) {
//                    return null;
//                }
//
//                @Override
//                public void cleanup() {
//
//                }
//
//                @Override
//                public String getId() {
//                    return model;
//                }
//
//                @Override
//                public void cancel() {
//
//                }
//            };
//        }
//    };

//    public static File getFile(Context context, Object model) {
//        try {
//            return Glide.with(context).load(model).downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    /**
     * 加载图片
     */
    public static void loadImage(Context context, final Object model, final OnLoadedListener onLoadedListener) {


//        Glide.with(context).load(model).apply().into(new SimpleTargetAdapter(model, onLoadedListener));
    }

    public static void loadImageFromCache(Context context, final String model, final OnLoadedListener onLoadedListener) {
//        Glide.with(context)
//                .using(cacheOnlyStreamLoader)
//                .load(model)
//                .asBitmap()
//                .into(new SimpleTargetAdapter(model, onLoadedListener));
    }

    private static abstract class SimpleTargetAdapter extends SimpleTarget<Bitmap> {

        private Object model;
        private OnLoadedListener onLoadedListener;

        public SimpleTargetAdapter(Object model, OnLoadedListener onLoadedListener) {
            this.model = model;
            this.onLoadedListener = onLoadedListener;
        }

//        @Override
//        public void onLoadFailed(@Nullable Drawable errorDrawable) {
//            super.onLoadFailed(errorDrawable);
//            if (onLoadedListener != null) {
//                onLoadedListener.onFail();
//            }
//        }
//
//        @Override
//        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition transition) {
//            if (onLoadedListener != null) {
//                onLoadedListener.onLoad(model, resource);
//            }
//        }
    }

    /**
     * 加载图片
     */
    public static void loadImage(final Object model, final OnLoadedListener onLoadedListener) {
//        RequestOptions options = new RequestOptions();
//        options.transform(new GlideRoundTransform(context, round));
//        Glide.with(BaseApplication.getContext()).load(model).into(new SimpleTargetAdapter(model, onLoadedListener));
//        Glide.with(BaseApplication.getContext()).load(model).into(new SimpleTarget<Bitmap>() {
//            @Override
//            public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
//                if (onLoadedListener != null) {
//                    onLoadedListener.onLoad(model, bitmap);
//                }
//            }
//        });
    }

    public static void  loadRoundImage(final Context context, Object model, ImageView imageView) {

    }



    /**
     * 加载图片
     */
    public static void loadRoundImage(final Context context, Object model, ImageView imageView, int round, int holder, int error) {
//        DrawableRequestBuilder<Object> inne = Glide.with(context).load(model).crossFade().placeholder(holder).error(error);
//        if (round > 0) {
//            inne.transform(new GlideRoundTransform(context, round)).into(imageView);
//        } else {
//            inne.into(imageView);
//        }
    }

    // ---------- 实际上，Glide 内部有对 Context 的类型做判断的，所以可以不需要这里 instanceof 判断
    /**
     * 加载图片
     */
    public static void loadImage(Context context, Object model, ImageView imageView, int holder) {
        if (context instanceof FragmentActivity) {
            loadImage((FragmentActivity) context, model, holder, 0, imageView);
        } else if (context instanceof Activity) {
            loadImage((Activity) context, model, holder, 0, imageView);
        } else {
            loadImage(context, model, holder, 0, imageView);
        }
    }

    /**
     * 加载图片
     */
    public static void loadImage(Context context, Object model, ImageView imageView) {
        loadImage(context, model, imageView, 0);
    }

    /**
     * 加载图片
     */
    public static void loadImage(ContextWrapper contextWrapper, Object model, ImageView imageView) {
        loadImage(contextWrapper, model, 0, 0, imageView);
    }

    /**
     * 加载图片
     */
    public static void loadImage(Activity activity, Object model, ImageView imageView) {
        loadImage(activity, model, 0, 0, imageView);
    }

    /**
     * 加载图片
     */
    public static void loadImage(FragmentActivity activity, Object model, ImageView imageView) {
        loadImage(activity, model, 0, 0, imageView);
    }

    /**
     * 加载图片
     */
    public static void loadImage(Fragment fragment, Object model, ImageView imageView) {
        loadImage(fragment, model, 0, 0, imageView);
    }

    /**
     * 加载图片
     */
    public static void loadImage(Context context, Object model, int placeholder, int error, ImageView imageView) {
        loadImage(context, model, placeholder, error, imageView, null);
    }

    /**
     * 加载图片
     */
    public static void loadImage(Activity activity, Object model, int placeholder, int error, ImageView imageView) {
        loadImage(activity, model, placeholder, error, imageView, null);
    }

    /**
     * 加载图片
     */
    public static void loadImage(FragmentActivity activity, Object model, int placeholder, int error, ImageView imageView) {
        loadImage(activity, model, placeholder, error, imageView, null);
    }

    /**
     * 加载图片
     */
    public static void loadImage(Fragment fragment, Object model, int placeholder, int error, ImageView imageView) {
        loadImage(fragment, model, placeholder, error, imageView, null);
    }

    // ----- 框架相关的都用 private，ContextWrapper 就是 Context，无需在针对 ContextWrapper 单独实现


    private static void loadImage(Context context, Object model, int placeholder, int error, ImageView imageView, RequestListener listener) {
        if (BaseActivity.assertFinishingOrDestoryed((Activity) context)) return;
        loadImage(Glide.with(context), model, placeholder, error, imageView, listener);
    }

    private static void loadImage(Activity activity, Object model, int placeholder, int error, ImageView imageView, RequestListener listener) {
        if (BaseActivity.assertFinishingOrDestoryed(activity)) return;
        loadImage(Glide.with(activity), model, placeholder, error, imageView, listener);
    }

    private static void loadImage(FragmentActivity activity, Object model, int placeholder, int error, ImageView imageView, RequestListener listener) {
        if (BaseActivity.assertFinishingOrDestoryed(activity)) return;
        loadImage(Glide.with(activity), model, placeholder, error, imageView, listener);
    }

    private static void loadImage(Fragment fragment, Object model, int placeholder, int error, ImageView imageView, RequestListener listener) {
        if (BaseActivity.assertFinishingOrDestoryed(fragment.getActivity())) return;
        loadImage(Glide.with(fragment), model, placeholder, error, imageView, listener);
    }

    private static void loadImage(RequestManager requestManager, Object model, int placeholder, int error, ImageView imageView, RequestListener listener) {
//
//        RequestOptions options = new RequestOptions();
//        options.error(error)
//                .placeholder(placeholder).diskCacheStrategy(DiskCacheStrategy.RESOURCE);
//
//        requestManager.load(model)
//                .apply(options)
////                .crossFade()//淡入淡出动画
//                .listener(listener)
//                .into(imageView);
//
////                requestManager.load(model)
////                        .apply(options)
////                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
////                .placeholder(placeholder).error(error)
//////                .crossFade()//淡入淡出动画
////                .listener(listener)
////                .into(imageView);
    }

    public interface OnLoadedListener {

        void onLoad(Object model, Bitmap bitmap);

        void onFail();
    }

    private static class GlideCircleTransform extends BitmapTransformation {

        public GlideCircleTransform(Context context) {
            super(context);
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return circleCrop(pool, toTransform);
        }

        private Bitmap circleCrop(BitmapPool pool, Bitmap source) {
            if (source == null) return null;

            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);

            Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);
            return result;
        }


        @Override
        public String getId() {
            return null;
        }
    }


    private static class GlideRoundTransform extends BitmapTransformation {

        private float radius = 0f;


        public GlideRoundTransform(Context context) {
            this(context, DensityUtil.dip2px(context, 6));
        }

        public GlideRoundTransform(Context context, int px) {
            super(context);
            this.radius = px;
        }

        @Override
        protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
            return roundCrop(pool, toTransform);
        }



        private Bitmap roundCrop(BitmapPool pool, Bitmap source) {
            if (source == null) return null;

            Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
            canvas.drawRoundRect(rectF, radius, radius, paint);
            return result;
        }

        @Override
        public String getId() {
            return null;
        }
    }
}
