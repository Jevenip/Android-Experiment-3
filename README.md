# 实验3 Android界面组件

## 实验环境
- 开发环境：Android 7.0
- 软件工具：Android Studio Koala 2024

## 实验内容与步骤

### 1. Android ListView 的用法
- **目标**: 利用 `SimpleAdapter` 实现指定的界面效果。
- **实现步骤**:
  1. 创建自定义布局文件 `list_item.xml`：
     ```xml
     <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
         android:orientation="horizontal"
         android:layout_width="match_parent"
         android:layout_height="wrap_content">
         <ImageView
             android:id="@+id/item_image"
             android:layout_width="50dp"
             android:layout_height="50dp"
             android:src="@drawable/sample_image" />
         <TextView
             android:id="@+id/item_text"
             android:layout_width="wrap_content"
             android:layout_height="wrap_content"
             android:layout_marginLeft="10dp"
             android:text="Item" />
     </LinearLayout>
     ```
  2. 在 `MainActivity.java` 中使用 `SimpleAdapter`：
     ```java
     ListView listView = findViewById(R.id.listView);
     List<Map<String, Object>> data = new ArrayList<>();
     for (int i = 0; i < 10; i++) {
         Map<String, Object> item = new HashMap<>();
         item.put("image", R.drawable.sample_image);
         item.put("text", "Item " + i);
         data.add(item);
     }
     SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.list_item,
             new String[]{"image", "text"}, new int[]{R.id.item_image, R.id.item_text});
     listView.setAdapter(adapter);
     
     listView.setOnItemClickListener((parent, view, position, id) -> {
         Toast.makeText(this, "Clicked: " + position, Toast.LENGTH_SHORT).show();
     });
     ```

---

### 2. 创建自定义布局的 AlertDialog
- **目标**: 实现一个带自定义布局的 `AlertDialog`。
- **实现步骤**:
  1. 创建自定义布局文件 `dialog_layout.xml`：
     ```xml
     <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
         android:orientation="vertical"
         android:layout_width="match_parent"
         android:layout_height="wrap_content">
         <EditText
             android:id="@+id/dialog_input"
             android:layout_width="match_parent"
             android:layout_height="wrap_content"
             android:hint="Enter something" />
         <Button
             android:id="@+id/dialog_button"
             android:layout_width="wrap_content"
             android:layout_height="wrap_content"
             android:text="Submit" />
     </LinearLayout>
     ```
  2. 在 `MainActivity.java` 中创建对话框：
     ```java
     View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_layout, null);
     AlertDialog dialog = new AlertDialog.Builder(this)
             .setTitle("Custom Dialog")
             .setView(dialogView)
             .setPositiveButton("OK", (dialogInterface, which) -> {
                 EditText input = dialogView.findViewById(R.id.dialog_input);
                 Toast.makeText(this, "You entered: " + input.getText(), Toast.LENGTH_SHORT).show();
             })
             .setNegativeButton("Cancel", null)
             .create();
     dialog.show();
     ```

---

### 3. 使用 XML 定义菜单
- **目标**: 定义菜单实现字体大小和颜色的设置。
- **实现步骤**:
  1. 创建菜单文件 `menu_main.xml`：
     ```xml
     <menu xmlns:android="http://schemas.android.com/apk/res/android">
         <item android:id="@+id/menu_small" android:title="Small Font" />
         <item android:id="@+id/menu_medium" android:title="Medium Font" />
         <item android:id="@+id/menu_large" android:title="Large Font" />
         <item android:id="@+id/menu_red" android:title="Red Text" />
         <item android:id="@+id/menu_black" android:title="Black Text" />
     </menu>
     ```
  2. 在 `MainActivity.java` 中加载菜单并处理点击：
     ```java
     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.menu_main, menu);
         return true;
     }
     
     @Override
     public boolean onOptionsItemSelected(@NonNull MenuItem item) {
         TextView textView = findViewById(R.id.textView);
         switch (item.getItemId()) {
             case R.id.menu_small:
                 textView.setTextSize(10);
                 break;
             case R.id.menu_medium:
                 textView.setTextSize(16);
                 break;
             case R.id.menu_large:
                 textView.setTextSize(20);
                 break;
             case R.id.menu_red:
                 textView.setTextColor(Color.RED);
                 break;
             case R.id.menu_black:
                 textView.setTextColor(Color.BLACK);
                 break;
         }
         return super.onOptionsItemSelected(item);
     }
     ```

---

### 4. 创建上下文操作模式的上下文菜单
- **目标**: 为 `ListView` 添加 `ActionMode` 上下文菜单。
- **实现步骤**:
  ```java
  listView.setOnItemLongClickListener((parent, view, position, id) -> {
      startActionMode(new ActionMode.Callback() {
          @Override
          public boolean onCreateActionMode(ActionMode mode, Menu menu) {
              MenuInflater inflater = mode.getMenuInflater();
              inflater.inflate(R.menu.context_menu, menu);
              return true;
          }
  
          @Override
          public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
              return false;
          }
  
          @Override
          public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
              switch (item.getItemId()) {
                  case R.id.action_edit:
                      Toast.makeText(MainActivity.this, "Edit selected", Toast.LENGTH_SHORT).show();
                      mode.finish();
                      return true;
                  case R.id.action_delete:
                      Toast.makeText(MainActivity.this, "Delete selected", Toast.LENGTH_SHORT).show();
                      mode.finish();
                      return true;
                  default:
                      return false;
              }
          }
  
          @Override
          public void onDestroyActionMode(ActionMode mode) {
          }
      });
      return true;
  });

## 自学内容
- **Android RecyclerView**:
  - **学习指南**: [RecyclerView 官方文档](https://developer.android.google.cn/guide/topics/ui/layout/recyclerview.html)
  - **示例代码**: [GitHub 示例](https://github.com/android/views-widgets-samples)
- **Card-Based Layout**:
  - **学习指南**: [CardView 官方文档](https://developer.android.google.cn/guide/topics/ui/layout/cardview.html#AddDependency)

## 实验总结
通过本次实验，熟悉了 Android 界面组件的基本使用方法，掌握了 `ListView`、`AlertDialog` 的自定义操作，以及通过 XML 定义菜单和上下文菜单的能力。同时，通过自学拓展了对 `RecyclerView` 和 `CardView` 的理解，为后续开发打下了基础。