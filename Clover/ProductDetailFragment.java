// file fragment_product_detail.xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    <androidx.viewpager.widget.ViewPager
        android:id="@+id/vpImagesContainer"
        android:layout_width="0dp"
        android:layout_height="350dp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
        
    <com.google.android.material.button.MaterialButton
        android:id="@+id/mbAddCart"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:text="Thêm vào giỏ hàng"
        android:textColor="@color/white"
        android:textAllCaps="false"
        app:backgroundTint="@color/black"
        app:cornerRadius="50dp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
// _______________________________________________________
        
// file ProductDetailFragment.java
import com.google.firebase.firestore.FirebaseFirestore;
import androidx.viewpager.widget.ViewPager;

public class ProductDetailFragment extends Fragment {
    
    ViewPager vpImagesContainer;
    MaterialButton mAddCart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);
        
        vpImagesContainer = view.findViewById(R.id.vpImagesContainer);
        FirebaseFirestore.getInstance()
        .collection("ProductModel").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<String> url = new ArrayList<>();
                for (QueryDocumentSnapshot snapshot : task.getResult())
                    url.add(snapshot.toObject(CartItemModel.class).getImage());
            
                vpImagesContainer.setAdapter(new ProductImageAdapter(url));
            }
        });

        mAddCart = view.findViewById(R.id.mbAddCart);
        mAdd

        return view;
    }

    public class ProductImageAdapter extends PagerAdapter {
    
        List<String> productImageList; // các url của các image
        
        // constructor
        public ProductImageAdapter(List<String> productImageList) {
            this.productImageList = productImageList;
        }
    
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            RoundedImageView productImage = new RoundedImageView(container.getContext()); // tạo image
            Glide.with(container.getContext()).load(productImageList.get(position)).into(productImage); // load url vào image
            container.addView(productImage, 0);
            return productImage;
        }
    
        @Override
        public int getCount() {
            return productImageList.size();
        }
    
        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }
    
    
        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((RoundedImageView) object);
        }
    }
}