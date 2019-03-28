package me.weicools.material.component.module;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.ButterKnife;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;
import java.util.List;
import me.weicools.material.component.Item;
import me.weicools.material.component.R;
import me.weicools.material.component.config.RouterPath;
import me.weicools.material.component.model.MnType;
import me.weicools.material.component.module.navigation.bottom.BottomNavigationBasicActivity;
import me.weicools.material.component.module.navigation.bottom.BottomNavigationDarkActivity;
import me.weicools.material.component.module.navigation.bottom.BottomNavigationIconActivity;
import me.weicools.material.component.module.navigation.bottom.BottomNavigationLightActivity;
import me.weicools.material.component.module.navigation.bottom.BottomNavigationMapBlueActivity;
import me.weicools.material.component.module.navigation.bottom.BottomNavigationPrimaryActivity;
import me.weicools.material.component.module.navigation.bottom.BottomNavigationShiftingActivity;
import me.weicools.material.component.module.sheet.bottom.BottomSheetBasicActivity;
import me.weicools.material.component.utils.SharedPref;
import me.weicools.material.component.utils.Tools;

/**
 * @author Weicools Create on 2018.08.23
 *
 * desc:
 */
@Route(path = RouterPath.MODULE_MAIN)
public class MainActivity extends AppCompatActivity {
  private ActionBar actionBar;
  private long exitTime = 0;

  private NestedScrollView nestedScrollview;
  private View notificationBadge;
  private int notificationCount = -1;

  private MnSearchAdapter searchAdapter;
  List<Item> searchItems = new ArrayList<>();
  private RecyclerView searchRecycler;
  private SharedPref sharedPref;
  private Toolbar toolbar;

  @Override
  protected void onCreate (Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    initToolbar();
    initDrawerMenu();
    initComponentMenu();
  }

  private void initToolbar () {
    this.toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(this.toolbar);
    this.actionBar = getSupportActionBar();
    this.actionBar.setDisplayHomeAsUpEnabled(true);
    this.actionBar.setHomeButtonEnabled(true);
    Tools.setSystemBarColorInt(this, Color.parseColor("#0A0A0A"));
  }

  private void initComponentMenu () {
    this.nestedScrollview = findViewById(R.id.nested_scrollview);
    RecyclerView recycler = findViewById(R.id.main_recycler);
    MnAdapter adapter = new MnAdapter(this, generateMenuItems(), (view, item) -> onMenuItemSelected(item));
    adapter.setMode(1);
    recycler.setLayoutManager(new LinearLayoutManager(this));
    recycler.setNestedScrollingEnabled(false);
    recycler.setAdapter(adapter);
    this.searchRecycler = findViewById(R.id.search_recycler);
    this.searchRecycler.setLayoutManager(new LinearLayoutManager(this));
    this.searchRecycler.setNestedScrollingEnabled(false);
    this.searchAdapter = new MnSearchAdapter(this, this.searchItems);
    this.searchRecycler.setAdapter(this.searchAdapter);
    this.searchAdapter.setOnItemClickListener((view, item, i) -> onMenuItemSelected(item));

    if (this.sharedPref.isFirstLaunch()) {
      showDialogAbout();
    }
  }

  private void onMenuItemSelected (Item item) {
    if (this.sharedPref.getClickSwitch()) {
      if (this.sharedPref.actionClickOffer()) {
        showDialogOffer();
        this.sharedPref.setClickSwitch(false);
        return;
      }
    } else if (this.sharedPref.actionClickInters()) {
      this.sharedPref.setClickSwitch(true);
    }
    if (item.Act != null) {
      startActivity(new Intent(this, item.Act));
      return;
    }
    if (item.Id == 1) {
      showDialogAbout();
    }
  }

  private List<Item> generateMenuItems () {
    List<Item> arrayList = new ArrayList<>();
    arrayList.add(new Item(-1, null, MnType.DIV));
    Item item = new Item(100, "Bottom Navigation", R.drawable.ic_view_column, MnType.HEAD);
    arrayList.add(item);
    arrayList.add(new Item(1000, item.Text, "Basic", MnType.SUB, BottomNavigationBasicActivity.class));
    arrayList.add(new Item(1002, item.Text, "Shifting", MnType.SUB, BottomNavigationShiftingActivity.class));
    arrayList.add(new Item(1003, item.Text, "Light", MnType.SUB, BottomNavigationLightActivity.class));
    arrayList.add(new Item(1004, item.Text, "Dark", MnType.SUB, BottomNavigationDarkActivity.class));
    arrayList.add(new Item(1005, item.Text, "Icon", MnType.SUB, BottomNavigationIconActivity.class));
    arrayList.add(new Item(1006, item.Text, "Primary", MnType.SUB, BottomNavigationPrimaryActivity.class));
    arrayList.add(new Item(1007, item.Text, "Map Blue", MnType.SUB, BottomNavigationMapBlueActivity.class));
    //arrayList.add(new Item(1008, item.Text, "Light Simple", MnType.SUB, BottomNavigationLightSimple.class));
    item = new Item(200, "Bottom Sheet", R.drawable.ic_call_to_actio, MnType.HEAD);
    arrayList.add(item);
    arrayList.add(new Item(2001, item.Text, "Basic", MnType.SUB, BottomSheetBasicActivity.class));
    //arrayList.add(new Item(202, item.Text, "List", MnType.SUB, BottomSheetList.class));
    //arrayList.add(new Item(203, item.Text, "Map", MnType.SUB, BottomSheetMap.class));
    //arrayList.add(new Item(204, item.Text, "Floating", MnType.SUB, BottomSheetFloating.class));
    //arrayList.add(new Item(205, item.Text, "Full", MnType.SUB, BottomSheetFull.class));
    //item = new Item(DialogFullscreen.DIALOG_QUEST_CODE, "Buttons", R.drawable.ic_touch_app, MnType.HEAD);
    //arrayList.add(item);
    //arrayList.add(new Item(301, item.Text, "Basic", MnType.SUB, ButtonBasic.class));
    //arrayList.add(new Item(302, item.Text, "Button In Utilities", MnType.SUB, ButtonInUtilities.class));
    //arrayList.add(new Item(303, item.Text, "Fab Middle", MnType.SUB, FabMiddle.class));
    //arrayList.add(new Item(304, item.Text, "Fab More", MnType.SUB, FabMore.class));
    //arrayList.add(new Item(305, item.Text, "Fab More Text", MnType.SUB, FabMoreText.class));
    //item = new Item(400, "Cards", R.drawable.ic_note, MnType.HEAD);
    //arrayList.add(item);
    //arrayList.add(new Item(401, item.Text, "Basic", MnType.SUB, CardBasic.class));
    //arrayList.add(new Item(402, item.Text, "Timeline", MnType.SUB, CardTimeline.class));
    //arrayList.add(new Item(403, item.Text, "Overlap", MnType.SUB, CardOverlap.class));
    //arrayList.add(new Item(404, item.Text, "Wizard", MnType.SUB, CardWizard.class));
    //arrayList.add(new Item(405, item.Text, "Wizard Light", MnType.SUB, CardWizardLight.class));
    //arrayList.add(new Item(406, item.Text, "Wizard Overlap", MnType.SUB, CardWizardOverlap.class));
    //item = new Item(500, "Chips", R.drawable.ic_label, MnType.HEAD);
    //arrayList.add(item);
    //arrayList.add(new Item(501, item.Text, "Basic", MnType.SUB, ChipBasic.class));
    //arrayList.add(new Item(502, item.Text, "Tag", MnType.SUB, ChipTag.class));
    //item = new Item(600, "Dialogs", R.drawable.ic_picture_in_picture, MnType.HEAD);
    //arrayList.add(item);
    //arrayList.add(new Item(601, item.Text, "Basic", MnType.SUB, DialogBasic.class));
    //arrayList.add(new Item(602, item.Text, "Fullscreen", MnType.SUB, DialogFullscreen.class));
    //arrayList.add(new Item(603, item.Text, "Custom", MnType.SUB, DialogCustom.class));
    //arrayList.add(new Item(604, item.Text, "Custom Info", MnType.SUB, DialogCustomInfo.class));
    //arrayList.add(new Item(605, item.Text, "Custom Warning", MnType.SUB, DialogCustomWarning.class));
    //arrayList.add(new Item(606, item.Text, "Custom Light", MnType.SUB, DialogCustomLight.class));
    //arrayList.add(new Item(607, item.Text, "Custom Dark", MnType.SUB, DialogCustomDark.class));
    //arrayList.add(new Item(608, item.Text, "Custom Add Post", MnType.SUB, DialogAddPost.class));
    //arrayList.add(new Item(609, item.Text, "Custom Add Review", MnType.SUB, DialogAddReview.class));
    //arrayList.add(new Item(610, item.Text, "GDPR Basic", MnType.SUB, DialogGDPRBasic.class));
    //arrayList.add(new Item(611, item.Text, "Term of Services", MnType.SUB, DialogTermOfServices.class));
    //arrayList.add(new Item(612, item.Text, "Header", MnType.SUB, DialogHeader.class));
    //arrayList.add(new Item(613, item.Text, "Image", MnType.SUB, DialogImage.class));
    //item = new Item(700, "Expansion Panels", R.drawable.ic_arrow_downward, MnType.HEAD);
    //arrayList.add(item);
    //arrayList.add(new Item(701, item.Text, "Basic", MnType.SUB, ExpansionPanelBasic.class));
    //arrayList.add(new Item(702, item.Text, "Invoice", MnType.SUB, ExpansionPanelInvoice.class));
    //arrayList.add(new Item(703, item.Text, "Ticket", MnType.SUB, ExpansionPanelTicket.class));
    //item = new Item(800, "Grid", R.drawable.ic_apps, MnType.HEAD);
    //arrayList.add(item);
    //arrayList.add(new Item(801, item.Text, "Basic", MnType.SUB, GridBasic.class));
    //arrayList.add(new Item(802, item.Text, "Single Line", MnType.SUB, GridSingleLine.class));
    //arrayList.add(new Item(803, item.Text, "Two Line", MnType.SUB, GridTwoLine.class));
    //arrayList.add(new Item(804, item.Text, "Sectioned", MnType.SUB, GridSectioned.class));
    //arrayList.add(new Item(805, item.Text, "Albums", MnType.SUB, GridAlbums.class));
    //arrayList.add(new Item(806, item.Text, "Caller", MnType.SUB, GridCaller.class));
    //Item item2 = new Item(900, "Lists", (int) R.drawable.ic_view_stream, true, MnType.HEAD);
    //arrayList.add(item2);
    //arrayList.add(new Item(901, item2.Text, "Basic", MnType.SUB, ListBasic.class));
    //arrayList.add(new Item(902, item2.Text, "Sectioned", MnType.SUB, ListSectioned.class));
    //arrayList.add(new Item(903, item2.Text, "Animation", MnType.SUB, ListAnimation.class));
    //arrayList.add(new Item(904, item2.Text, "Expand", MnType.SUB, ListExpand.class));
    //arrayList.add(new Item(905, item2.Text, "Draggable", MnType.SUB, ListDrag.class));
    //arrayList.add(new Item(906, item2.Text, "Swipe", MnType.SUB, ListSwipe.class));
    //arrayList.add(new Item(907, item2.Text, "Multi Selection", MnType.SUB, ListMultiSelection.class));
    //arrayList.add(new Item(908, item2.Text, "News Light", true, MnType.SUB, ListNewsLight.class));
    //arrayList.add(new Item(909, item2.Text, "News Light Horizontal", true, MnType.SUB, ListNewsLightHorizontal.class));
    //arrayList.add(new Item(910, item2.Text, "News Card", true, MnType.SUB, ListNewsCard.class));
    //arrayList.add(new Item(911, item2.Text, "News Image", true, MnType.SUB, ListNewsImage.class));
    //item = new Item(2000, "Menu", R.drawable.ic_reoder, MnType.HEAD);
    //arrayList.add(item);
    //arrayList.add(new Item(2001, item.Text, "Drawer News", MnType.SUB, MenuDrawerNews.class));
    //arrayList.add(new Item(2002, item.Text, "Drawer Mail", MnType.SUB, MenuDrawerMail.class));
    //arrayList.add(new Item(2003, item.Text, "Drawer Simple Light", MnType.SUB, MenuDrawerSimpleLight.class));
    //arrayList.add(new Item(2004, item.Text, "Drawer Simple Dark", MnType.SUB, MenuDrawerSimpleDark.class));
    //arrayList.add(new Item(2005, item.Text, "Drawer No Icon", MnType.SUB, MenuDrawerNoIcon.class));
    //arrayList.add(new Item(2006, item.Text, "Overflow Toolbar", MnType.SUB, MenuOverflowToolbar.class));
    //arrayList.add(new Item(2007, item.Text, "Overflow List", MnType.SUB, MenuOverflowList.class));
    //item = new Item(1000, "Pickers", R.drawable.ic_event, MnType.HEAD);
    //arrayList.add(item);
    //arrayList.add(new Item(1001, item.Text, "Date Light", MnType.SUB, PickerDateLight.class));
    //arrayList.add(new Item(1002, item.Text, "Date Dark", MnType.SUB, PickerDateDark.class));
    //arrayList.add(new Item(PointerIconCompat.TYPE_HELP, item.Text, "Time Light", MnType.SUB, PickerTimeLight.class));
    //arrayList.add(new Item(PointerIconCompat.TYPE_WAIT, item.Text, "Time Dark", MnType.SUB, PickerTimeDark.class));
    //arrayList.add(new Item(1005, item.Text, "Color RGB", MnType.SUB, PickerColor.class));
    //arrayList.add(
    //    new Item((int) PointerIconCompat.TYPE_CELL, item.Text, HttpHeaders.LOCATION, MnType.SUB, PickerLocation.class));
    //item = new Item(1100, "Progress & Activity", R.drawable.ic_settings_backup_restore, MnType.HEAD);
    //arrayList.add(item);
    //arrayList.add(new Item(1101, item.Text, "Basic", MnType.SUB, ProgressBasic.class));
    //arrayList.add(new Item(1102, item.Text, "Linear Center", MnType.SUB, ProgressLinearCenter.class));
    //arrayList.add(new Item(1103, item.Text, "Linear Top", MnType.SUB, ProgressLinearTop.class));
    //arrayList.add(new Item(1104, item.Text, "Circle Center", MnType.SUB, ProgressCircleCenter.class));
    //arrayList.add(new Item(1105, item.Text, "On Scroll", MnType.SUB, ProgressOnScroll.class));
    //arrayList.add(new Item(1106, item.Text, "Pull Refresh", MnType.SUB, ProgressPullRefresh.class));
    //arrayList.add(new Item(1107, item.Text, "Dots Bounce", MnType.SUB, ProgressDotsBounce.class));
    //arrayList.add(new Item(1108, item.Text, "Dots Fade", MnType.SUB, ProgressDotsFade.class));
    //arrayList.add(new Item(1109, item.Text, "Dots Grow", MnType.SUB, ProgressDotsGrow.class));
    //item = new Item(1200, "Sliders", R.drawable.ic_tune, MnType.HEAD);
    //arrayList.add(item);
    //arrayList.add(new Item(1201, item.Text, "Light", MnType.SUB, SliderLight.class));
    //arrayList.add(new Item(1202, item.Text, "Dark", MnType.SUB, SliderDark.class));
    //arrayList.add(new Item(1203, item.Text, "Color Picker", MnType.SUB, SliderColorPicker.class));
    //item = new Item(1300, "Snackbars & Toasts", R.drawable.ic_wb_iridescent, MnType.HEAD);
    //arrayList.add(item);
    //arrayList.add(new Item(1301, item.Text, "Basic", MnType.SUB, SnackbarToastBasic.class));
    //arrayList.add(new Item(1302, item.Text, "Lift FAB", MnType.SUB, SnackbarAndFab.class));
    //arrayList.add(new Item(1303, item.Text, "Toast Custom", MnType.SUB, ToastCustom.class));
    //arrayList.add(new Item(1304, item.Text, "Snackbar Custom", MnType.SUB, SnackbarCustom.class));
    //item = new Item(1400, "Steppers", R.drawable.ic_timeline, MnType.HEAD);
    //arrayList.add(item);
    //arrayList.add(new Item(1401, item.Text, "Text", MnType.SUB, StepperText.class));
    //arrayList.add(new Item(1402, item.Text, "Dots", MnType.SUB, StepperDots.class));
    //arrayList.add(new Item(1403, item.Text, "Progress", MnType.SUB, StepperProgress.class));
    //arrayList.add(new Item(1404, item.Text, "Vertical", MnType.SUB, StepperVertical.class));
    //arrayList.add(new Item(1405, item.Text, "Wizard Light", MnType.SUB, StepperWizardLight.class));
    //arrayList.add(new Item(1406, item.Text, "Wizard Color", MnType.SUB, StepperWizardColor.class));
    //item = new Item(ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED, "Tabs", R.drawable.ic_tabs, MnType.HEAD);
    //arrayList.add(item);
    //arrayList.add(new Item(1501, item.Text, "Basic", MnType.SUB, TabsBasic.class));
    //arrayList.add(new Item(1502, item.Text, "Store", MnType.SUB, TabsStore.class));
    //arrayList.add(new Item(1503, item.Text, "Light", MnType.SUB, TabsLight.class));
    //arrayList.add(new Item(1504, item.Text, "Dark", MnType.SUB, TabsDark.class));
    //arrayList.add(new Item(1505, item.Text, "Icon", MnType.SUB, TabsIcon.class));
    //arrayList.add(new Item(1506, item.Text, "Text & Icon", MnType.SUB, TabsTextIcon.class));
    //arrayList.add(new Item(1507, item.Text, "Icon Light", MnType.SUB, TabsIconLight.class));
    //arrayList.add(new Item(1508, item.Text, "Icon Stack", MnType.SUB, TabsIconStack.class));
    //arrayList.add(new Item(1509, item.Text, "Scroll", MnType.SUB, TabsScroll.class));
    //arrayList.add(new Item(1510, item.Text, "Round", MnType.SUB, TabsRound.class));
    //item = new Item(1600, "Form", R.drawable.ic_assignment, MnType.HEAD);
    //arrayList.add(item);
    //arrayList.add(new Item(1601, item.Text, "Login", MnType.SUB, FormLogin.class));
    //arrayList.add(new Item(1602, item.Text, "Sign Up", MnType.SUB, FormSignUp.class));
    //arrayList.add(new Item(1603, item.Text, "Profile Data", MnType.SUB, FormProfileData.class));
    //arrayList.add(new Item(1604, item.Text, "With Icon", MnType.SUB, FormWithIcon.class));
    //arrayList.add(new Item(1605, item.Text, "Text Area", MnType.SUB, FormTextArea.class));
    //arrayList.add(new Item(1606, item.Text, "Address", MnType.SUB, FormAddress.class));
    //arrayList.add(new Item(1607, item.Text, "Checkout", MnType.SUB, FormCheckout.class));
    //arrayList.add(new Item(1608, item.Text, "Ecommerce", MnType.SUB, FormEcommerce.class));
    //arrayList.add(new Item(1609, item.Text, "Sign Up Card", MnType.SUB, FormSignupCard.class));
    //arrayList.add(new Item(1610, item.Text, "Sign Up Card Stack", MnType.SUB, FormSignupCardStack.class));
    //arrayList.add(new Item(1611, item.Text, "Sign Up Dark", MnType.SUB, FormSignupDark.class));
    //arrayList.add(new Item(1612, item.Text, "Sign Up Image", MnType.SUB, FormSignupImage.class));
    //arrayList.add(new Item(1613, item.Text, "Sign Up Image Card", MnType.SUB, FormSignupImageCard.class));
    //arrayList.add(new Item(1614, item.Text, "Sign Up Image Outline", MnType.SUB, FormSignupImageOutline.class));
    //item = new Item(1700, "Toolbars", R.drawable.ic_web_asset, MnType.HEAD);
    //arrayList.add(item);
    //arrayList.add(new Item(1701, item.Text, "Basic", MnType.SUB, ToolbarBasic.class));
    //arrayList.add(new Item(1702, item.Text, "Collapse", MnType.SUB, ToolbarCollapse.class));
    //arrayList.add(new Item(1703, item.Text, "Collapse And Pin", MnType.SUB, ToolbarCollapsePin.class));
    //arrayList.add(new Item(1704, item.Text, "Light", MnType.SUB, ToolbarLight.class));
    //arrayList.add(new Item(1705, item.Text, "Dark", MnType.SUB, ToolbarDark.class));
    //arrayList.add(new Item(-1, "Extra", MnType.DIV));
    //Item item3 = new Item(1800, "Profile", (int) R.drawable.ic_person, true, MnType.HEAD);
    //arrayList.add(item3);
    //arrayList.add(new Item(1801, item3.Text, "Polygon", MnType.SUB, ProfilePolygon.class));
    //arrayList.add(new Item(1802, item3.Text, "Purple", MnType.SUB, ProfilePurple.class));
    //arrayList.add(new Item(1803, item3.Text, "Red", MnType.SUB, ProfileRed.class));
    //arrayList.add(new Item(1804, item3.Text, "Blue Appbar", MnType.SUB, ProfileBlueAppbar.class));
    //arrayList.add(new Item(1805, item3.Text, "Image Appbar", MnType.SUB, ProfileImageAppbar.class));
    //arrayList.add(new Item(1806, item3.Text, "Drawer Simple", MnType.SUB, ProfileDrawerSimple.class));
    //arrayList.add(new Item(1807, item3.Text, "Drawer Image", MnType.SUB, ProfileDrawerImage.class));
    //arrayList.add(new Item(1808, item3.Text, "Gallery", MnType.SUB, ProfileGallery.class));
    //arrayList.add(new Item(1809, item3.Text, "Gallery Two", MnType.SUB, ProfileGalleryTwo.class));
    //arrayList.add(new Item(1810, item3.Text, "Card List", MnType.SUB, ProfileCardList.class));
    //arrayList.add(new Item(1811, item3.Text, "Fab Menu", MnType.SUB, ProfileFabMenu.class));
    //arrayList.add(new Item(1812, item3.Text, "Card Header", MnType.SUB, ProfileCardHeader.class));
    //arrayList.add(new Item(1813, item3.Text, "Card Header Image", MnType.SUB, ProfileCardHeaderImage.class));
    //arrayList.add(new Item(1814, item3.Text, "Card Overlap", MnType.SUB, ProfileCardOverlap.class));
    //arrayList.add(new Item(1815, item3.Text, "Formal", MnType.SUB, ProfileFormal.class));
    //arrayList.add(new Item(1816, item3.Text, "Freelancer", MnType.SUB, ProfileFreelancer.class));
    //arrayList.add(new Item(1817, item3.Text, "Rating", MnType.SUB, ProfileRating.class));
    //arrayList.add(new Item(1818, item3.Text, "Skills", MnType.SUB, ProfileSkills.class));
    //arrayList.add(new Item(1819, item3.Text, "Wallet", true, MnType.SUB, ProfileWallet.class));
    //arrayList.add(new Item(1820, item3.Text, "Tab", true, MnType.SUB, ProfileTab.class));
    //arrayList.add(new Item(1821, item3.Text, "Pink", true, MnType.SUB, ProfilePink.class));
    //arrayList.add(new Item(1822, item3.Text, "White", true, MnType.SUB, ProfileWhite.class));
    //arrayList.add(new Item(1823, item3.Text, "Dark", true, MnType.SUB, ProfileDark.class));
    //item = new Item(19000, "No Item Page", R.drawable.ic_do_not_disturb_off, MnType.HEAD);
    //arrayList.add(item);
    //arrayList.add(new Item(19001, item.Text, "Archived", MnType.SUB, NoItemArchived.class));
    //arrayList.add(new Item(19002, item.Text, "Search", MnType.SUB, NoItemSearch.class));
    //arrayList.add(new Item(19003, item.Text, "Internet Icon", MnType.SUB, NoItemInternetIcon.class));
    //arrayList.add(new Item(19004, item.Text, "Internet Image", MnType.SUB, NoItemInternetImage.class));
    //arrayList.add(new Item(19005, item.Text, "Bg City", MnType.SUB, NoItemBgCity.class));
    //arrayList.add(new Item(19006, item.Text, "Bg Cactus", MnType.SUB, NoItemBgCactus.class));
    //arrayList.add(new Item(19007, item.Text, "Tabs", MnType.SUB, NoItemTabs.class));
    //item = new Item(20000, "Player", R.drawable.ic_live_tv, MnType.HEAD);
    //arrayList.add(item);
    //arrayList.add(new Item(20001, item.Text, "Music Basic", MnType.SUB, PlayerMusicBasic.class));
    //arrayList.add(new Item(20002, item.Text, "Music Light", MnType.SUB, PlayerMusicLight.class));
    //arrayList.add(new Item(20003, item.Text, "Music Album Dark", MnType.SUB, PlayerMusicAlbumDark.class));
    //arrayList.add(new Item(20004, item.Text, "Music Album Circle", MnType.SUB, PlayerMusicAlbumCircle.class));
    //arrayList.add(new Item(20005, item.Text, "Music Album Simple", MnType.SUB, PlayerMusicAlbumSimple.class));
    //arrayList.add(new Item(20006, item.Text, "Music Song List", MnType.SUB, PlayerMusicSongList.class));
    //arrayList.add(new Item(20007, item.Text, "Music Album Grid", MnType.SUB, PlayerMusicAlbumGrid.class));
    //arrayList.add(new Item(20008, item.Text, "Music Tabs", MnType.SUB, PlayerMusicTabs.class));
    //arrayList.add(new Item(20009, item.Text, "Music Tabs Icon", MnType.SUB, PlayerMusicTabsIcon.class));
    //arrayList.add(new Item(20010, item.Text, "Music Genre", MnType.SUB, PlayerMusicGenre.class));
    //arrayList.add(new Item(20011, item.Text, "Music Genre Image", MnType.SUB, PlayerMusicGenreImage.class));
    //arrayList.add(new Item(20012, item.Text, "Music Genre Light", MnType.SUB, PlayerMusicGenreLight.class));
    //arrayList.add(new Item(20013, item.Text, "Video Basic", MnType.SUB, PlayerVideoBasic.class));
    //arrayList.add(new Item(20014, item.Text, "Video Simple", MnType.SUB, PlayerVideoSimple.class));
    //item = new Item(21000, "Timeline", R.drawable.ic_wrap_text, MnType.HEAD);
    //arrayList.add(item);
    //arrayList.add(new Item(21001, item.Text, "Timeline Feed", MnType.SUB, TimelineFeed.class));
    //arrayList.add(new Item(21002, item.Text, "Timeline Path", MnType.SUB, TimelinePath.class));
    //arrayList.add(new Item(21003, item.Text, "Timeline Dot Card", MnType.SUB, TimelineDotCard.class));
    //arrayList.add(new Item(21004, item.Text, "Timeline Twitter", MnType.SUB, TimelineTwitter.class));
    //arrayList.add(new Item(21005, item.Text, "Timeline Simple", MnType.SUB, TimelineSimple.class));
    //item = new Item(22000, "Shopping", R.drawable.ic_shopping_cart, MnType.HEAD);
    //arrayList.add(item);
    //arrayList.add(new Item(22001, item.Text, "Category List", MnType.SUB, ShoppingCategoryList.class));
    //arrayList.add(new Item(22002, item.Text, "Category Card", MnType.SUB, ShoppingCategoryCard.class));
    //arrayList.add(new Item(22003, item.Text, "Category Image", MnType.SUB, ShoppingCategoryImage.class));
    //arrayList.add(new Item(22004, item.Text, "Sub Category Tabs", MnType.SUB, ShoppingSubCategoryTabs.class));
    //arrayList.add(new Item(22005, item.Text, "Product Grid", MnType.SUB, ShoppingProductGrid.class));
    //arrayList.add(new Item(22006, item.Text, "Product Details", MnType.SUB, ShoppingProductDetails.class));
    //arrayList.add(new Item(22007, item.Text, "Product Adv Details", MnType.SUB, ShoppingProductAdvDetails.class));
    //arrayList.add(new Item(22008, item.Text, "Checkout Card", MnType.SUB, ShoppingCheckoutCard.class));
    //arrayList.add(new Item(22009, item.Text, "Checkout Step", MnType.SUB, ShoppingCheckoutStep.class));
    //arrayList.add(new Item(22010, item.Text, "Checkout One Page", MnType.SUB, ShoppingCheckoutOnePage.class));
    //arrayList.add(new Item(22011, item.Text, "Checkout Timeline", MnType.SUB, ShoppingCheckoutTimeline.class));
    //arrayList.add(new Item(22012, item.Text, "Cart Simple", MnType.SUB, ShoppingCartSimple.class));
    //arrayList.add(new Item(22013, item.Text, "Cart Card", MnType.SUB, ShoppingCartCard.class));
    //arrayList.add(new Item(22014, item.Text, "Cart Dark", MnType.SUB, ShoppingCartCardDark.class));
    //item2 = new Item(23000, "Search Page", (int) R.drawable.ic_search, true, MnType.HEAD);
    //arrayList.add(item2);
    //arrayList.add(new Item(23001, item2.Text, "Toolbar Light", MnType.SUB, SearchToolbarLight.class));
    //arrayList.add(new Item(23002, item2.Text, "Toolbar Dark", MnType.SUB, SearchToolbarDark.class));
    //arrayList.add(new Item(23003, item2.Text, "Store", MnType.SUB, SearchStore.class));
    //arrayList.add(new Item(23004, item2.Text, "Primary", MnType.SUB, SearchPrimary.class));
    //arrayList.add(new Item(23005, item2.Text, "Primary Bg", MnType.SUB, SearchPrimaryBg.class));
    //arrayList.add(new Item(23006, item2.Text, "History Card", MnType.SUB, SearchHistoryCard.class));
    //arrayList.add(new Item(23007, item2.Text, "City", MnType.SUB, SearchCity.class));
    //arrayList.add(new Item(23008, item2.Text, "Filter Hotel", MnType.SUB, SearchFilterHotel.class));
    //arrayList.add(new Item(23009, item2.Text, "Filter Product", MnType.SUB, SearchFilterProduct.class));
    //arrayList.add(new Item(23010, item2.Text, "Filter Property", MnType.SUB, SearchFilterProperty.class));
    //arrayList.add(new Item(23011, item2.Text, "Event Type", true, MnType.SUB, SearchEventType.class));
    //arrayList.add(new Item(23012, item2.Text, "Suggestion", true, MnType.SUB, SearchSuggestion.class));
    //arrayList.add(new Item(23013, item2.Text, "Suggestion Red", true, MnType.SUB, SearchSuggestionRed.class));
    //arrayList.add(new Item(23014, item2.Text, "Outlet", true, MnType.SUB, SearchOutlet.class));
    //arrayList.add(new Item(23015, item2.Text, "Outlet Yellow", true, MnType.SUB, SearchOutletYellow.class));
    //item = new Item(24000, "Slider Image", R.drawable.ic_photo_library, MnType.HEAD);
    //arrayList.add(item);
    //arrayList.add(new Item(24001, item.Text, "Header", MnType.SUB, SliderImageHeader.class));
    //arrayList.add(new Item(24002, item.Text, "Header Auto", MnType.SUB, SliderImageHeaderAuto.class));
    //arrayList.add(new Item(24003, item.Text, "Card", MnType.SUB, SliderImageCard.class));
    //arrayList.add(new Item(24004, item.Text, "Card Auto", MnType.SUB, SliderImageCardAuto.class));
    //item = new Item(25000, "Settings", R.drawable.ic_settings, MnType.HEAD);
    //arrayList.add(item);
    //arrayList.add(new Item(25001, item.Text, "Sectioned", MnType.SUB, SettingSectioned.class));
    //arrayList.add(new Item(25002, item.Text, "Flat", MnType.SUB, SettingFlat.class));
    //arrayList.add(new Item(25003, item.Text, "Profile", MnType.SUB, SettingProfile.class));
    //arrayList.add(new Item(25004, item.Text, "Profile Light", MnType.SUB, SettingProfileLight.class));
    //item = new Item(26000, "Verification", R.drawable.ic_check_circle, MnType.HEAD);
    //arrayList.add(item);
    //arrayList.add(new Item(26001, item.Text, "Phone", MnType.SUB, VerificationPhone.class));
    //arrayList.add(new Item(26002, item.Text, "Code", MnType.SUB, VerificationCode.class));
    //arrayList.add(new Item(26003, item.Text, "Header", MnType.SUB, VerificationHeader.class));
    //arrayList.add(new Item(26004, item.Text, "Image", MnType.SUB, VerificationImage.class));
    //arrayList.add(new Item(26005, item.Text, "Blue", MnType.SUB, VerificationBlue.class));
    //arrayList.add(new Item(26006, item.Text, "Orange", MnType.SUB, VerificationOrange.class));
    //item = new Item(27000, "Login", R.drawable.ic_https, MnType.HEAD);
    //arrayList.add(item);
    //arrayList.add(new Item(27001, item.Text, "Simple Light", MnType.SUB, LoginSimpleLight.class));
    //arrayList.add(new Item(27002, item.Text, "Simple Dark", MnType.SUB, LoginSimpleDark.class));
    //arrayList.add(new Item(27003, item.Text, "Simple Green", MnType.SUB, LoginSimpleGreen.class));
    //arrayList.add(new Item(27004, item.Text, "Image Teal", MnType.SUB, LoginImageTeal.class));
    //arrayList.add(new Item(27005, item.Text, "Card Light", MnType.SUB, LoginCardLight.class));
    //arrayList.add(new Item(27006, item.Text, "Card Overlap", MnType.SUB, LoginCardOverlap.class));
    //item = new Item(28000, "Payment", R.drawable.ic_monetization_on, MnType.HEAD);
    //arrayList.add(item);
    //arrayList.add(new Item(28001, item.Text, "Card Collections", MnType.SUB, PaymentCardCollections.class));
    //arrayList.add(new Item(28002, item.Text, "Card Details", MnType.SUB, PaymentCardDetails.class));
    //arrayList.add(new Item(28003, item.Text, "Form", MnType.SUB, PaymentForm.class));
    //arrayList.add(new Item(28004, item.Text, "Profile", MnType.SUB, PaymentProfile.class));
    //arrayList.add(new Item(28005, item.Text, "Success Dialog", MnType.SUB, PaymentSuccessDialog.class));
    //Item item4 = new Item(29000, "Dashboard", (int) R.drawable.ic_event_seat, true, MnType.HEAD);
    //arrayList.add(item4);
    //arrayList.add(new Item(29001, item4.Text, "Grid Fab", MnType.SUB, DashboardGridFab.class));
    //arrayList.add(new Item(29002, item4.Text, "Statistics", MnType.SUB, DashboardStatistics.class));
    //arrayList.add(new Item(29003, item4.Text, "Pay Bill", MnType.SUB, DashboardPayBill.class));
    //arrayList.add(new Item(29004, item4.Text, "Flight", MnType.SUB, DashboardFlight.class));
    //arrayList.add(new Item(29005, item4.Text, "Wallet", MnType.SUB, DashboardWallet.class));
    //arrayList.add(new Item(29006, item4.Text, "Wallet Green", MnType.SUB, DashboardWalletGreen.class));
    //arrayList.add(new Item(29007, item4.Text, "Finance", MnType.SUB, DashboardFinance.class));
    //arrayList.add(new Item(29008, item4.Text, "Cryptocurrency", MnType.SUB, DashboardCryptocurrency.class));
    //arrayList.add(new Item(29009, item4.Text, "Dana", true, MnType.SUB, DashboardDana.class));
    //arrayList.add(new Item(29010, item4.Text, "Travel", true, MnType.SUB, DashboardTravel.class));
    //arrayList.add(new Item(29011, item4.Text, "News", true, MnType.SUB, DashboardNews.class));
    //item = new Item(30000, "Article", R.drawable.ic_subject, MnType.HEAD);
    //arrayList.add(item);
    //arrayList.add(new Item(30001, item.Text, "Simple", MnType.SUB, ArticleSimple.class));
    //arrayList.add(new Item(30002, item.Text, "Medium", MnType.SUB, ArticleMedium.class));
    //arrayList.add(new Item(30003, item.Text, "Medium Dark", MnType.SUB, ArticleMediumDark.class));
    //arrayList.add(new Item(30004, item.Text, "Big Header", MnType.SUB, ArticleBigHeader.class));
    //arrayList.add(new Item(30005, item.Text, "Stepper", MnType.SUB, ArticleStepper.class));
    //arrayList.add(new Item(30006, item.Text, "Card", MnType.SUB, ArticleCard.class));
    //arrayList.add(new Item(30007, item.Text, "Food", MnType.SUB, ArticleFood.class));
    //arrayList.add(new Item(30008, item.Text, "Food Review", MnType.SUB, ArticleFoodReview.class));
    //item = new Item(31000, "About", R.drawable.ic_perm_device_info, MnType.HEAD);
    //arrayList.add(item);
    //arrayList.add(new Item(31001, item.Text, "App", MnType.SUB, AboutApp.class));
    //arrayList.add(new Item(31002, item.Text, "App Simple", MnType.SUB, AboutAppSimple.class));
    //arrayList.add(new Item(31003, item.Text, "App Simple Blue", MnType.SUB, AboutAppSimpleBlue.class));
    //arrayList.add(new Item(31004, item.Text, "Company", MnType.SUB, AboutCompany.class));
    //arrayList.add(new Item(31005, item.Text, "Company Image", MnType.SUB, AboutCompanyImage.class));
    //arrayList.add(new Item(31006, item.Text, "Company Card", MnType.SUB, AboutCompanyCard.class));
    //arrayList.add(new Item(31007, item.Text, "Dialog Main Action", MnType.SUB, AboutDialogMainAction.class));
    //item4 = new Item(32000, "Chat", (int) R.drawable.ic_chat, true, MnType.HEAD);
    //arrayList.add(item4);
    //arrayList.add(new Item(32001, item4.Text, "Telegram", true, MnType.SUB, ChatTelegram.class));
    //arrayList.add(new Item(32002, item4.Text, "WhatsApp", true, MnType.SUB, ChatWhatsapp.class));
    //arrayList.add(new Item(32003, item4.Text, "Facebook", true, MnType.SUB, ChatFacebook.class));
    //arrayList.add(new Item(32004, item4.Text, "BBM", true, MnType.SUB, ChatBBM.class));
    //arrayList.add(new Item(-1, "Application", MnType.DIV));
    //arrayList.add(new Item(1, "About", R.drawable.ic_error_outline, MnType.NOR));
    this.searchItems.clear();
    for (Item item5 : arrayList) {
      if (item5.ItemType == MnType.SUB.getValue()) {
        this.searchItems.add(item5);
      }
    }
    return arrayList;
  }

  private void setupBadge () {
    if (this.notificationBadge != null) {
      if (this.notificationCount == 0) {
        this.notificationBadge.setVisibility(View.GONE);
      } else {
        this.notificationBadge.setVisibility(View.VISIBLE);
      }
    }
  }

  private void initDrawerMenu () {
    NavigationView navigationView = findViewById(R.id.nav_view);
    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    final NavigationView navigationView2 = navigationView;
    ActionBarDrawerToggle anonymousClass6 =
        new ActionBarDrawerToggle(this, drawer, this.toolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close) {
          @Override
          public void onDrawerOpened (View view) {
            updateCounter(navigationView2);
            super.onDrawerOpened(view);
          }
        };
    drawer.setDrawerListener(anonymousClass6);
    anonymousClass6.syncState();
    navigationView.setNavigationItemSelectedListener(menuItem -> {
      int itemId = menuItem.getItemId();
      if (itemId == R.id.action_portfolio) {
        Tools.openInAppBrowser(MainActivity.this, "http://portfolio.dream-space.web.id/", false);
      } else if (itemId == R.id.action_notifications) {
        // TODO: 2019/3/27 fcm
        //ActivityNotifications.navigate(this);
      } else if (itemId == R.id.action_rate) {
        Tools.rateAction(MainActivity.this);
      } else if (itemId == R.id.action_about) {
        showDialogAbout();
      }
      return true;
    });
    View navigation_header = navigationView.getHeaderView(0);
    TextView textView = navigation_header.findViewById(R.id.tv_new_version);
    ImageButton imageButton = navigation_header.findViewById(R.id.bt_update);
    // TODO: 2019/3/27 update version
    //if (((long) Tools.getVersionCode(this)) >= this.remoteConfig.getAppVersion().longValue()) {
    //  textView.setVisibility(View.GONE);
    //  imageButton.setVisibility(View.GONE);
    //}
    imageButton.setOnClickListener(view -> Tools.rateAction(this));
  }

  private void updateCounter (NavigationView navigationView) {
    View findViewById =
        navigationView.getMenu().findItem(R.id.action_notifications).getActionView().findViewById(R.id.notif_badge);
    findViewById.setVisibility(notificationCount == 0 ? View.GONE : View.VISIBLE);
  }

  @Override
  public boolean onCreateOptionsMenu (Menu menu) {
    getMenuInflater().inflate(R.menu.menu_activity_main, menu);
    Tools.changeMenuIconColor(menu, -1);
    Tools.changeOverflowMenuIconColor(this.toolbar, -1);
    final MenuItem findItem = menu.findItem(R.id.action_notifications);
    View actionView = MenuItemCompat.getActionView(findItem);
    this.notificationBadge = actionView.findViewById(R.id.notif_badge);
    setupBadge();
    actionView.setOnClickListener(view -> onOptionsItemSelected(findItem));
    MenuItem findItem2 = menu.findItem(R.id.action_search);
    ((SearchView) MenuItemCompat.getActionView(findItem2)).setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit (String str) {
        searchAdapter.getFilter().filter(str);
        return false;
      }

      @Override
      public boolean onQueryTextChange (String str) {
        searchAdapter.getFilter().filter(str);
        return false;
      }
    });

    MenuItemCompat.setOnActionExpandListener(findItem2, new MenuItemCompat.OnActionExpandListener() {
      @Override
      public boolean onMenuItemActionCollapse (MenuItem menuItem) {
        nestedScrollview.setVisibility(View.VISIBLE);
        searchRecycler.setVisibility(View.GONE);
        findItem.setVisible(true);
        initToolbar();
        initDrawerMenu();
        return true;
      }

      @Override
      public boolean onMenuItemActionExpand (MenuItem menuItem) {
        nestedScrollview.setVisibility(View.GONE);
        searchRecycler.setVisibility(View.VISIBLE);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setHomeButtonEnabled(false);
        findItem.setVisible(false);
        return true;
      }
    });

    return true;
  }

  @Override
  public boolean onOptionsItemSelected (MenuItem item) {
    if (item.getItemId() == R.id.action_notifications) {
      // TODO: 2019/3/27 fcm
      //ActivityNotifications.navigate(this);
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onBackPressed () {
    super.onBackPressed();
    doExitApp();
  }

  public void doExitApp () {
    if (System.currentTimeMillis() - this.exitTime > 2000) {
      Toast.makeText(this, "Press again to exit app", Toast.LENGTH_SHORT).show();
      this.exitTime = System.currentTimeMillis();
      return;
    }
    finish();
  }

  private void showDialogAbout () {
    final Dialog dialog = new Dialog(this);
    dialog.requestWindowFeature(1);
    dialog.setContentView(R.layout.dialog_about);
    dialog.setCancelable(true);
    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
    layoutParams.copyFrom(dialog.getWindow().getAttributes());
    layoutParams.width = -2;
    layoutParams.height = -2;
    ((TextView) dialog.findViewById(R.id.tv_version)).setText("Version 2.3");
    dialog.findViewById(R.id.bt_getcode).setOnClickListener(view -> {
      Intent intent = new Intent("android.intent.action.VIEW");
      intent.setData(Uri.parse("https://codecanyon.net/user/dream_space/portfolio"));
      startActivity(intent);
    });
    (dialog.findViewById(R.id.bt_close)).setOnClickListener(view -> dialog.dismiss());
    (dialog.findViewById(R.id.bt_rate)).setOnClickListener(view -> Tools.rateAction(this));
    (dialog.findViewById(R.id.bt_portfolio)).setOnClickListener(
        view -> Tools.openInAppBrowser(this, "http://portfolio.dream-space.web.id/", false));
    this.sharedPref.setFirstLaunch(false);
    dialog.show();
    dialog.getWindow().setAttributes(layoutParams);
  }

  private void showDialogOffer () {
    Dialog dialog = new Dialog(this);
    dialog.requestWindowFeature(1);
    dialog.setContentView(R.layout.dialog_offer);
    dialog.setCancelable(true);
    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
    layoutParams.copyFrom(dialog.getWindow().getAttributes());
    layoutParams.width = -2;
    layoutParams.height = -2;
    dialog.findViewById(R.id.bt_getcode).setOnClickListener(view -> {
      Intent intent = new Intent("android.intent.action.VIEW");
      intent.setData(Uri.parse("https://codecanyon.net/user/dream_space/portfolio"));
      startActivity(intent);
    });
    this.sharedPref.setFirstLaunch(false);
    dialog.show();
    dialog.getWindow().setAttributes(layoutParams);
  }
}
