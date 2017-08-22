package wuxc.single.railwayparty.start;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import wuxc.single.railwayparty.R;
import wuxc.single.railwayparty.internet.HttpGetData;
import wuxc.single.railwayparty.internet.URLcontainer;
import wuxc.single.railwayparty.layout.RoundImageView;

public class SpecialDetailActivity3 extends Activity implements OnClickListener, OnItemClickListener {
	// private EditText EditAnswer;
	// private Button BtnAnswer;
	private ImageView ImageBack;
	private ImageView ImagePic;
	// private ScrollView ScrollLayout;
	private ListView ListData;
	// private TextView TextWarning;
	private TextView TextDetail;
	private TextView TextTime;
	private TextView TextName;
	private TextView TextTitle;

	private String Name;
	private String Title;
	private String Time;
	// List<CommentModel> list = new ArrayList<CommentModel>();
	// private static CommentAdapter mAdapter;
	private int pageSize = 10;
	private int totalPage = 5;
	private int curPage = 1;
	private int screenwidth = 0;
	private float scale = 0;
	private float scalepx = 0;
	private float dp = 0;
	private String detail = "此次专项检查的范围是招用农民工较多的建筑、制造、采矿、餐饮和其他中小型劳动密集型企业以及个体经济组织。检查内容包括：非公企业与劳动者签订劳动合同情况；按照工资支付有关规定支付职工工资情况；遵守最低工资规定及依法支付加班工资情况；依法参加社会保险和缴纳社会保险费情况；遵守禁止使用童工规定以及女职工和未成年工特殊劳动保护规定情况；其他遵守劳动保障法律法规的情况。";
	private String Id = "";
	private String ticket="";
	private String chn;
	private String userPhoto;
	private String LoginId;
	private WebView webView;
	private static final String GET_SUCCESS_RESULT = "success";
	private static final String GET_FAIL_RESULT = "fail";
	private static final int GET_DUE_DATA = 6;
	private TextView text_detail;
	private String content;
	private SharedPreferences PreUserInfo;// 存储个人信息
	private Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				// GetData();
				// Toast.makeText(getApplicationContext(), "正在加载数据",
				// Toast.LENGTH_SHORT).show();
				show();
				break;
			case GET_DUE_DATA:
				GetDataDueData(msg.obj);
				break;
			default:
				break;

			}
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.special_detail_activity4);
		// Intent intent = this.getIntent(); // 获取已有的intent对象
		// Bundle bundle = intent.getExtras(); // 获取intent里面的bundle对象
		// Name = bundle.getString("Name");
		// Title = bundle.getString("Title");
		// Time = bundle.getString("Time");
		// Id = bundle.getString("Id");
		//
		// chn = bundle.getString("chn");
		// try {
		// detail = bundle.getString("detail");
		// ticket = bundle.getInt("ticket");
		// } catch (Exception e) {
		// // TODO: handle exception
		// }
		PreUserInfo = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
		ReadTicket();
		initview();
		setonclicklistener();
		setlistheight(0);
		settext();
		starttimedelay();
		TextName.setText("作者：" + "系统管理员");
		TextTime.setText("2017");
		String html = "<html>" + "<body>" + "<table>" + "<tr>" + "<td>成都天府</td>" + "</tr>" + "</table>" + "</body>"
				+ "</html>";
		text_detail.setText("摘要：" + detail);
		// detail = " <div class=card_content
		// id=\"js-card-content\"><p>党组织关系转接就是指党员因调动工作、参军、学习、外出等原因离开原所在地或单位，以及外出时间在六个月以上，且地点比较固定的，经党组织同意，将党组织进行转接的一种调动形式。</p></div>
		// <div class=intro_button></div> </div> <div class=\"starVideo
		// starVideo1\" id=starVideo> </div> </div> <div class=\"entry-detail\">
		// <div class=\"entry-basic\"> <div id=\"basic-info\"> <a
		// name=uni_baseinfo></a> <h2 class=titleH2><span
		// class=h2mark></span>基本信息</h2> <div class=\"card-list-box\"> <ul
		// class=cardlist> <li> <div class=\"cardlist-con\"> <p
		// class=\"cardlist-name\" title=\"中文名称\">中文名称</p> <p
		// class=\"cardlist-value\" title=\"党组织关系转接\">党组织关系转接</p> </div> </li>
		// <li> <div class=\"cardlist-con\"> <p class=\"cardlist-name\"
		// title=\"外文名称\">外文名称</p> <p class=\"cardlist-value\" title=\"Transfer
		// relationship between the party organization\">Transfer relationship
		// between the party organization</p> </div> </li> <li> <div
		// class=\"cardlist-con\"> <p class=\"cardlist-name\" title=\"人 员\">人
		// 员</p> <p class=\"cardlist-value\" title=\"党员\">党员</p> </div> </li>
		// </ul> <ul class=cardlist> <li> <div class=\"cardlist-con\"> <p
		// class=\"cardlist-name\" title=\"原 因\">原 因</p> <p
		// class=\"cardlist-value\" title=\"调动工作、参军、学习、外出\">调动工作、参军、学习、外出</p>
		// </div> </li> <li> <div class=\"cardlist-con\"> <p
		// class=\"cardlist-name\" title=\"性 质\">性 质</p> <p
		// class=\"cardlist-value\" title=\"调动形式\">调动形式</p> </div> </li> </ul>
		// </div> </div> </div><div class=\"entry-before\"> </div> <div
		// class=\"entry-catalogue\"> <div class=\"entry-mod-catalogue\"> <div
		// class=catalogModWrap> <span class=h2mark></span> <table
		// class=catalogMod id=\"J-ext-mod-topcatalog\"> <tr>
		// <th><span><em></em>目录</span></th> <td class=listTd> <div><em>1</em><a
		// title=\"定义\" href=\"#5400047-5637620-1\">定义</a></div>
		// <div><em>2</em><a title=\"转移办法\"
		// href=\"#5400047-5637620-2\">转移办法</a></div> </td><td class=listTd>
		// <div><em>3</em><a title=\"转接步骤\"
		// href=\"#5400047-5637620-3\">转接步骤</a></div> <div><em>4</em><a
		// title=\"转接要求\" href=\"#5400047-5637620-4\">转接要求</a></div> </td><td
		// class=listTd> <div><em>5</em><a title=\"注意事项\"
		// href=\"#5400047-5637620-5\">注意事项</a></div> </td> </tr> </table>
		// </div> </div> </div><div class=\"entry-body\"> </div> <div
		// class=\"entry-content\"> <div id=\"lemma-main\"
		// class=\"lemma-meaning\" data-sub=\"#5400047-5637620-0\"> <div
		// class=\"main_content_text cl\" id=\"main-content-text\"> <h2> <a
		// name=\"5400047-5637620-1\"></a> <a class=conArrow href=\"#\"
		// data-logid=\"h2-title\">折叠</a> <span class=\"opt js-edittext\"> <a
		// class=edit href=\"/create/edit/?eid=5400047&sid=5637620&secid=1\"
		// data-log=\"edit-title\"><i class=ico></i>编辑本段</a></span> <b
		// class=title>定义</b></h2> <div class=\"sonConBox \"> <div
		// class=h2_content>
		// <p>党组织关系转接就是指党员因调动工作、参军、学习、外出等原因离开原所在地或单位，以及外出时间在六个月以上，且地点比较固定的，经党组织同意，将党组织进行转接的一种调动形式。<a
		// href=\"https://p1.ssl.qhmsg.com/t01e96705a39cf9bdfb.jpg\"
		// class=\"show-img layoutright\" data-type=\"gallery\"><span
		// class=\"show-img-hd\" style=\"width:220px;height:310px;\"><img
		// id=\"img_6923201\" title=\"党组织关系转接\" alt=\"党组织关系转接\"
		// data-img=\"mod_img\"
		// data-src=\"https://p1.ssl.qhmsg.com/dr/220__/t01e96705a39cf9bdfb.jpg\"
		// style=\"width:220px;height:310px;\" /></span><span
		// class=\"show-img-bd\">党组织关系转接</span></a></p> </div> </div> <h2> <a
		// name=\"5400047-5637620-2\"></a> <a class=conArrow href=\"#\"
		// data-logid=\"h2-title\">折叠</a> <span class=\"opt js-edittext\"> <a
		// class=edit href=\"/create/edit/?eid=5400047&sid=5637620&secid=2\"
		// data-log=\"edit-title\"><i class=ico></i>编辑本段</a></span> <b
		// class=title>转移办法</b></h2> <div class=\"sonConBox \"> <div
		// class=h2_content> <p>1、党员因调动工作、参军、学习、<a target=\"_blank\"
		// href=\"/doc/633426-670384.html\">外出务工经商</a>和其他原因离开原所在地或单位，以及外出时间在六个月以上，且地点比较固定的，经党组织同意，应按规定转移党员正式组织关系（即开写党员组织关系介绍信）。</p><p>2、在全国范围内可以直接相互转移党组织关系的党组织</p><p>（1）县级及县级以上地方各级党委组织部；（2）中央直属机关各部门、中央国家机关各部门及中央一级人民团体的机关党委；（3）省、<a
		// target=\"_blank\"
		// href=\"/doc/5411133-5649231.html\">自治区</a>、直辖市直属机关党委（工委）组织部；（4）省、自治区、直辖市各工作委员会组织部（处）；（5）新疆<a
		// target=\"_blank\"
		// href=\"/doc/3954608-4149845.html\">生产建设</a>兵团政治部组织部、各农业师（管理局）政治部（处）；（6）中国人民解放军师（旅）或相当于师以上政治部或其组织部门；（7）铁路系统的各铁路局党委组织部。</p><p>3、<a
		// target=\"_blank\"
		// href=\"/doc/2495086-2636695.html\">复转</a>军人党员组织关系转递的<a
		// target=\"_blank\"
		// href=\"/doc/2042470-2161163.html\">基本原则</a></p><p>已落实了工作单位的，应及时将党员组织关系转到所去单位党组织。因某些原因尚未落实工作的，可将党员组织关系转到父母、配偶居住地的街道、乡镇党组织。党员在落实单位过程中将人事关系和档案材料等暂保存在县以上政府人事（民政）部门所属复转安置部门的，这些部门的党组织如具备管理条件并经同级地方党委同意，也可以接收这些党员的组织关系，并根据不同情况，组织这些党员过组织生活。</p><p>4、<a
		// target=\"_blank\"
		// href=\"/doc/6753720-6968297.html\">大中专毕业生</a>党员组织关系转递的基本原则</p><p>已经落实了工作单位的，应将党员组织关系及时转到所去单位党组织。党员在落实工作单位过程中将人事关系和档案材料等暂保存在县以上政府人事（劳动）部门所属的人才交流<a
		// target=\"_blank\"
		// href=\"/doc/4989151-5212831.html\">服务机构</a>的，这些机构的党组织如具备条件并经同级地方党委同意，可以接收这部分党员的组织关系，并根据不同情况，组织这些党员过组织生活。对因某些原因，一时还不能落实工作单位的，可将其党员组织关系转移到本人或父母居住地的街道、乡镇党组织。</p><p>5、<a
		// target=\"_blank\"
		// href=\"/doc/4061200-4259603.html\">离退休干部</a>、职工党员组织关系转递的原则</p><p>（1）离退休干部的组织关系一般保留在原单位，可单独组建党支部。（2）就地安置的退休工人和退职干部、工人党员，其<a
		// target=\"_blank\"
		// href=\"/doc/7890462-8164557.html\">党的组织关系</a>一般应转到居住地区街道（或村）党组织。（3）易地安置的离退休、退职干部、工人党员，其党的组织关系应转移到接受安置地区街道、乡（镇）或村党组织。（4）离退休干部、工人党员因看病、探望子女和亲属，外出时间超过六个月以上的，所在单位的党组织应给他们开具党员<a
		// target=\"_blank\"
		// href=\"/doc/5417618-5655766.html\">证明信</a>，所到单位或地区的党组织应接收并安排其参加<a
		// target=\"_blank\"
		// href=\"/doc/6751690-6966255.html\">党的组织生活</a>。（5）干部、工人党员离休、退休、退职后，又受聘到另一单位工作，如果时间在半年以上，应将其党员组织关系转移到新的工作单位党组织。</p>
		// </div> </div> <h2> <a name=\"5400047-5637620-3\"></a> <a
		// class=conArrow href=\"#\" data-logid=\"h2-title\">折叠</a> <span
		// class=\"opt js-edittext\"> <a class=edit
		// href=\"/create/edit/?eid=5400047&sid=5637620&secid=3\"
		// data-log=\"edit-title\"><i class=ico></i>编辑本段</a></span> <b
		// class=title>转接步骤</b></h2> <div class=\"sonConBox \"> <div
		// class=h2_content> <p>1、党员经所在党支部同意，由党支部开出从支部到上一级党委（<a
		// target=\"_blank\"
		// href=\"/doc/6231415-6444748.html\">党工委</a>）的组织关系介绍信。</p><p>2、党员持支部开出的介绍信到上一级党委（党工委），党委（党工委）核实后，根据支部开出的介绍信，分三种不同情况开出相应的介绍信。（1）如果党员转往该党委（党工委）下属的其他支部，则开出从党委（党工委）到转入支部的介绍信；（2）如果党员转往市内其他党委（党工委），则开出从所在党委（党工委）到转入党委（党工委）的介绍信，党员持介绍信，到转入党委（党工委）办理；（3）如果党员转往市外有关单位，则开出从党委（党工委）到<a
		// target=\"_blank\"
		// href=\"/doc/6777372-6993228.html\">市委组织部</a>的介绍信。</p><p>3、党员持党委（党工委）开出的组织关系介绍信到市委组织部。市委组织部经核实后，根据党委（党工委）开出的介绍信情况，开出从市委组织部到市外相应有转接权限党委组织部门的介绍信；</p><p>4、党员持市委组织开出的组织关系介绍信到市外相应有转接权限的党委组织部门，该组织部门开出从组织部门到转入党委（党工委）的组织关系介绍信；</p><p>5、党员持市外有转接权限党委组织部门介绍信到转入党委（党工委），该党委（党工委）根据介绍信情况，开出从党委（党工委）到转入支部的介绍信；</p><p>6、党员持转入党委（党工委）开出的介绍到转入支部报到。</p>
		// </div> </div> <h2> <a name=\"5400047-5637620-4\"></a> <a
		// class=conArrow href=\"#\" data-logid=\"h2-title\">折叠</a> <span
		// class=\"opt js-edittext\"> <a class=edit
		// href=\"/create/edit/?eid=5400047&sid=5637620&secid=4\"
		// data-log=\"edit-title\"><i class=ico></i>编辑本段</a></span> <b
		// class=title>转接要求</b></h2> <div class=\"sonConBox \"> <div
		// class=h2_content> <p>1、开具党员组织关系介绍信要使用统一式样的\"<a target=\"_blank\"
		// href=\"/doc/1475840-1560614.html\">中国共产党</a>党员组织关系介绍信\"，用钢笔或毛笔填写，字迹要清楚，不得涂改。应在介绍信和存根上注明有效期。党员组织关系介绍信必须加盖公章，并加盖<a
		// target=\"_blank\"
		// href=\"/doc/6306155-6519706.html\">骑缝章</a>。介绍信的有效期可根据具体情况确定，一般不应超过3个月。党员组织关系介绍信由党员自己携带，不能携带的，应由机要交通或机要邮政转递。</p><p>2、党员组织关系介绍信一旦丢失，要及时向所在单位党组织或最后办理转移组织关系的党委组织部门报告，由组织部门进行审查。如果确系本人不慎，由最后办理转移组织关系的党组织予以补转，并立即通知<a
		// target=\"_blank\"
		// href=\"/doc/321648-340666.html\">接收单位</a>党组织，原介绍信作废。对丢失介绍信的党员，应给予批评教育，情节严重的还要给予适当的党纪处分。</p><p>3、党员应及时转移党组织关系，对过期的党员组织关系介绍信，组织部门要调查了解。无正当理由不及时转移组织关系，要予以严肃的批评和教育。其中超过六个月不参加组织生活的，按照党章规定作自行脱党处理。过期的党员组织关系介绍信作废，由开出介绍信单位另行补转。</p><p>4、党委在党员组织关系转接过程中要及时做好党员档案的转接工作，转出单位要及时把转出党员的档案及时寄往转入单位；转入单位要主动和转出单位联系。</p><p>接转党员组织关系，分为党员组织关系介绍信和党员证明信两种。</p><p>1．党员组织关系介绍信</p><p>(1)党员组织关系介绍信的含义</p><p>党员组织关系介绍信是党员变动组织关系的凭证。党员因工作单位发生变化，外出学习或<a
		// target=\"_blank\"
		// href=\"/doc/6291470-6504975.html\">工作时间</a>在六个月以上且地点比较固定的，应按规定转移正式组织关系，即开写党员组织关系介绍信。党员组织关系转出后，党员在党组织中的隶属关系随即发生变化，党员应在转入单位党组织参加党的组织生活，交纳党费。</p><p>(2)接转党员组织关系介绍信的<a
		// target=\"_blank\"
		// href=\"/doc/631461-668300.html\">一般程序</a></p><p>①党员需转移组织关系时，经党组织批准方可办理党员组织关系转移手续。</p><p>②党员在转移组织关系时，应由其所在党支部开出证明，由党员本人持证明到上级党委组织部门办理转移手续。</p><p>③组织部门开具党员组织关系介绍信要使用统一样式的“<a
		// target=\"_blank\"
		// href=\"/doc/164159-173484.html\">中国共产党党员</a>组织关系介绍信”，用毛笔或钢笔填写，字迹要清楚，不得涂改。如涂改更正，须加盖更正章。要写明党员转出和接收单位的全称，要用大写字注明党费交至的月份。要根据被介绍人的实际情况在介绍信和存根上注明<a
		// target=\"_blank\"
		// href=\"/doc/3933015-4127503.html\">有效期限</a>，一般不应超过三个月。党员组织关系介绍信必须加盖公章，并在介绍信和存根的连接部位加盖骑缝章。每张介绍信原则上只限一人使用。如果因整个党组织隶属关系变动需要办理转移党员组织关系手续，可使用一张介绍信，但应另附名单，并加盖组织部门的印章。</p><p>④党员组织关系介绍信由党员自己携带，自己不能携带的，应由机要交通或机要邮政转递。</p><p>⑤党员必须在介绍信注明的有效期内办理党组织关系接转手续。转出单位的党组织应负责督促党员按期转移组织关系。对那些没有正当理由不按期到指定单位接转党组织关系的党员，应给予严肃的批评教育。经教育仍不改正的，原所在单位党组织应视情况给予党纪处分。介绍信超过有效期限六个月党员仍无故不办理党组织关系接转手续的，应按党章规定作自行脱党处理。</p><p>⑥各级党委组织部门在接收党员组织关系介绍信时，要认真审查核对，对不符合接转手续要求的党员组织关系介绍信，要退给党员本人，让其按规定重新办理接转手续。</p><p>⑦党员组织介绍信一旦丢失，要立即向所在单位的党组织或最后办理转移组织关系的党委组织部门报告。党组织应对丢失介绍信的情况进行审查，如确系本人不慎丢失，可由最后办理转移关系的党组织予以补转，并立即通知新单位党组织，原介绍信作废。</p><p>2．党员证明信</p><p>(1)党员证明信的用途</p><p>党员证明信是党员临时外出参加党的组织生活的凭证，即党员临时组织关系凭证。党员临时外出时，持党员证明信以证明其党员身份。党员证明信一般只限在党员临时外出六个月以内使用。持党员证明信的正式党员，其党组织关系没有从原所在的党组织转移出去，仍在原单位参加党的组织生活，交纳党费和享有表决权、<a
		// target=\"_blank\"
		// href=\"/doc/9062166-9393195.html\">选举权和被选举权</a>。</p><p>(2)开具党员证明信的要求</p><p>开具党员证明信，应由党支部写出证明，由基层党委负责办理。开具党员证明信，应用统—印制的式样。证明信应写明党员的姓名、性别、职务及是正式党员还是<a
		// target=\"_blank\"
		// href=\"/doc/334625-354439.html\">预备党员</a>，并注明证明信的使用有效期限。党员证明信应统一编号，加盖基层党委公章。党员证明信一般由本人亲自递交所去的党组织，也可以由开具党员证明信的基层党委寄出。</p>
		// </div> </div> <h2> <a name=\"5400047-5637620-5\"></a> <a
		// class=conArrow href=\"#\" data-logid=\"h2-title\">折叠</a> <span
		// class=\"opt js-edittext\"> <a class=edit
		// href=\"/create/edit/?eid=5400047&sid=5637620&secid=5\"
		// data-log=\"edit-title\"><i class=ico></i>编辑本段</a></span> <b
		// class=title>注意事项</b></h2> <div class=\"sonConBox \"> <div
		// class=h2_content> <p>党员组织关系介绍信，是党员政治<a target=\"_blank\"
		// href=\"/doc/6499632-6713347.html\">身份的证明</a>。党员应谨慎保存党员组织关系介绍信，并及时办理转移手续。</p><p>一、党员临时外出到另一单位去工作学习，时间在六个月以内的，应持“中国共产党党员证明信”（即临时组织关系），证明党员身份，其组织关系没有转移，仍在原单位参加党的组织生活、交纳党费和享有表决权、选举权和被选举权。</p><p>二、党员因工作单位发生变化、外出学习或工作，时间在六个月以上，且地点比较固定的，应持“<a
		// target=\"_blank\"
		// href=\"/doc/5376128-5612242.html\">中国共产党党员组织关系介绍信</a>”转移组织关系。党员组织关系转出后，其隶属关系随即发生变化，党员应在转入单位党组织参加党的组织生活、交纳党费和享有表决权、选举权和被选举权。</p><p>三、短期外出（六个月以内）或长期外出但没有固定地点暂时无法转移组织关系的党员，可持《<a
		// target=\"_blank\"
		// href=\"/doc/1920113-2031430.html\">流动党员</a>活动证》在外出所在地或单位的党组织参加<a
		// target=\"_blank\"
		// href=\"/doc/5488265-5726177.html\">党的生活</a>，交纳党费，但不享有表决权、选举权和被选举权。《流动党员活动证》由基层党委负责发放。接收流动党员的党组织对持有《流动党员活动证》的外来党员，应于验证后及时接收并将其编入党支部、党小组，同时报上级党组织备案。</p><p>四、<a
		// target=\"_blank\"
		// href=\"/doc/6808494-7025447.html\">离退休人员</a>中的党员，就地安置并归原单位管理的，组织关系仍留原单位，可视其党员人数的多少，成立离退休人员党支部、党小组；易地安置的，或虽就地安置，因身体等方面原因不便参加原单位党的生活的，应将组织关系转到居住地党组织。</p><p>五、大中专毕业生、复转军人中的党员，已落实了工作单位的，应及时将组织关系转到所去单位党组织。因某些原因，一时还不能落实工作单位的，可将其组织关系转到本人、配偶或父母居住地党组织。</p><p>六、停薪留职人员中的党员，应在办理停薪留职手续的时候将组织关系转到居住地党组织。</p><p>七、党员因私事短期出国、出境或<a
		// target=\"_blank\"
		// href=\"/doc/220144-232878.html\">自费出国留学</a>，应办理保留党籍手续，其组织关系保留在原单位党组织。出国出境定居的党员，应办理停止党籍手续。</p><p>八、援外人员中的党员组织关系，由派出单位统一转移。党支部开具党员组织关系介绍信时，必须注明党员的具体去向和所担负的任务，最后由省、市（自治区）党的委员会组织部协调，统一转至外交部政治部，再由外交部转往我国驻外使馆的党的委员会。</p><p>九、恢复党籍的同志如果在恢复党籍之前已调动了工作，要由作出恢复党籍决定的党组织开具党员组织关系介绍信，并经基层党委办理党员组织关系转移手续。</p><p>十、党员在支部范围内调动工作或临时变动党小组，不必开具党员组织关系介绍信，也不必开具党员证明信。</p><p>十一、党支部在填写党员组织关系介绍信时，应在介绍信和存根上注明有效期限，并在介绍信和存根的连接部分加盖印章，介绍信的有效期一般不超过15天。</p><p>十二、如果党员成批调往新单位，党支部可在开具组织关系介绍信后另附名单，所附名单要加盖印章。正式党员和预备党员要分别开具组织关系介绍信。</p><p>十三、<a
		// target=\"_blank\"
		// href=\"/doc/1935272-2047449.html\">县直机关</a>各党支部党员关系的转移，必须经过系统党委办理转移手续。</p><p>十四、党员转移组织关系，应由本人亲自携带组织关系介绍信办理，<a
		// target=\"_blank\"
		// href=\"/doc/5703890-5916607.html\">不能自己</a>携带的，应由党内交通或机要邮政传递。</p><p>十五、党员组织关系介绍信或证明信一旦丢失，应及时向所在单位党组织报告，党组织应进行审查，如确系本人不慎丢失，应写出书面检讨，可由最后办理转移组织关系的党组织予以补转，并立即通知接收单位党组织，原介绍信或证明信作废。</p><p>十六、党员自带组织关系应及时转移。对于那些无正当理由，不及时转移组织关系，导致过期的，应给予严肃的批评教育。其中超过六个月不参加党的组织生活的，按自行脱党处理。</p>
		// </div> </div> </div> </div> </div><div class=\"entry-after\"> <div
		// id=characterRelationship style=\"display:none;padding:0 30px\"></div>
		// </div> <div class=\"entry-belong\"> <div class=\"reinforce cl\">
		// </div> </div> </div> </div> <div class=\"boxinner-r\"> <span
		// class=\"dw-ad-box js-wd-ad-box\" style=\"display:none\"> <a
		// href=\"###\" target=_blank id=dwAdBox> </a> <span
		// class=\"ico-adclose\"></span> </span> <div id=\"card-image\"> <div
		// class=img> <a data-type=gallery
		// href=\"https://p1.ssl.qhmsg.com/dr/270_500_/t01bfa90a36e26476f3.jpg\"
		// target=_blank> <img id=\"js-entry-image\" class=pic width=270
		// src=\"https://p1.ssl.qhmsg.com/dr/270_500_/t01bfa90a36e26476f3.jpg?size=268x200\"
		// alt=\"党组织关系转接\" title=\"\"> </a> </div> <div class=desc> <a
		// id=entry_search_url
		// href=\"http://image.so.com/i?src=360baike_sidepicmore&q=%E5%85%9A%E7%BB%84%E7%BB%87%E5%85%B3%E7%B3%BB%E8%BD%AC%E6%8E%A5\"
		// target=_blank>党组织关系转接</a> </div> <div class=bline1></div> <div
		// class=bline2></div> </div> <div id=weiboshow></div> <div
		// class=right_high_top></div> <div class=\"doc-interest\"
		// id=baike_interests> <h2></h2> <div class=\"cont items\"> <div
		// class=\"cont-img\"> <a href=\"###\" target=_blank> <img> </a> </div>
		// </div> </div> <div class=\"doc-rightslide\"
		// id='js-doc-rightslide'></div> <div id=\"J-ext-mod-nlpmodule-2\"
		// style=\"display:none\"> ";
		// if (chn.equals("wsdx")) {
		webView = (android.webkit.WebView) findViewById(R.id.webview);
		// StringBuilder sb = new StringBuilder();
		// sb.append(detail);
		Log.e("here", "here");
		// webView.loadUrl("http://ww.baidu.com");
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebChromeClient(new WebChromeClient());
		webView.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		webView.loadUrl("http://211.149.216.199:9050/console/pb/orgTree/orgTree");
		// webView.loadDataWithBaseURL(URLcontainer.urlip, detail, "text/html",
		// "utf-8", null);

		// } else {
		// GetData();
		// }

		// detail=getNewContent(detail);
		final ArrayList ArrayValues = new ArrayList();
		// ArrayValues.add(new BasicNameValuePair("ticket", ticket));
		// ArrayValues.add(new BasicNameValuePair("applyType", "" + 2));
		// ArrayValues.add(new BasicNameValuePair("helpSType", "" +
		// type));
		// ArrayValues.add(new BasicNameValuePair("modelSign",
		// "KNDY_APPLY"));
		// ArrayValues.add(new BasicNameValuePair("curPage", "" +
		// curPage));
		// ArrayValues.add(new BasicNameValuePair("pageSize", "" +
		// pageSize));
		// final ArrayList ArrayValues = new ArrayList();
		ArrayValues.add(new BasicNameValuePair("ticket", "" + ticket));
		// chn = GetChannelByKey.GetSign(PreALLChannel, "职工之家");
		ArrayValues.add(new BasicNameValuePair("chn", chn));

		ArrayValues.add(new BasicNameValuePair("datakey", "" + Id));

		new Thread(new Runnable() { // 开启线程上传文件
			@Override
			public void run() {
				String DueData = "";
				DueData = HttpGetData.GetData("api/cms/common/browserModelItem", ArrayValues);

			}
		}).start();
	}

	private void ReadTicket() {
		// TODO Auto-generated method stub
		ticket = PreUserInfo.getString("ticket", "");

	}

	private void GetData() {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		final ArrayList ArrayValues = new ArrayList();
		// ArrayValues.add(new BasicNameValuePair("ticket", ticket));
		// ArrayValues.add(new BasicNameValuePair("applyType", "" + 2));
		// ArrayValues.add(new BasicNameValuePair("helpSType", "" + type));
		// ArrayValues.add(new BasicNameValuePair("modelSign", "KNDY_APPLY"));
		// ArrayValues.add(new BasicNameValuePair("curPage", "" + curPage));
		// ArrayValues.add(new BasicNameValuePair("pageSize", "" + pageSize));
		// final ArrayList ArrayValues = new ArrayList();
		ArrayValues.add(new BasicNameValuePair("ticket", "" + ticket));
		// chn = GetChannelByKey.GetSign(PreALLChannel, "职工之家");
		ArrayValues.add(new BasicNameValuePair("chn", chn));
		ArrayValues.add(new BasicNameValuePair("datakey", "" + Id));

		new Thread(new Runnable() { // 开启线程上传文件
			@Override
			public void run() {
				String DueData = "";
				DueData = HttpGetData.GetData("api/cms/channel/channleContentData", ArrayValues);
				Message msg = new Message();
				msg.obj = DueData;
				msg.what = GET_DUE_DATA;
				uiHandler.sendMessage(msg);
			}
		}).start();

	}

	private void show() {
		// TODO Auto-generated method stub
		webView = (android.webkit.WebView) findViewById(R.id.webview);
		// StringBuilder sb = new StringBuilder();
		// sb.append(detail);
		Log.e("here", "here1");
		// webView.loadUrl("http://ww.baidu.com");
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebChromeClient(new WebChromeClient());
		webView.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);

		webView.loadDataWithBaseURL(URLcontainer.urlip, content, "text/html", "utf-8", null);

	}

	private String getNewContent(String htmltext) {

		Document doc = Jsoup.parse(htmltext);
		Elements elements = doc.getElementsByTag("img");
		for (Element element : elements) {
			element.attr("width", "100%").attr("height", "auto");
		}

		Log.d("VACK", doc.toString());
		return doc.toString();
	}

	protected void GetDataDueData(Object obj) {

		// TODO Auto-generated method stub
		// String Type = null;
		String Data = null;
		// String pager = null;
		try {
			JSONObject demoJson = new JSONObject(obj.toString());
			String Type = demoJson.getString("type");

			Data = demoJson.getString("data");
			if (Type.equals(GET_SUCCESS_RESULT)) {

				GetDataList(Data, curPage);
			} else if (Type.equals(GET_FAIL_RESULT)) {
				Toast.makeText(getApplicationContext(), "服务器数据失败", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getApplicationContext(), "数据格式校验失败", Toast.LENGTH_SHORT).show();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// private void GetDataList(String data, int arg) {
	//
	// if (arg == 1) {
	// list.clear();
	// }
	// JSONArray jArray = null;
	// try {
	// jArray = new JSONArray(data);
	// JSONObject json_data = null;
	// if (jArray.length() == 0) {
	// Toast.makeText(getApplicationContext(), "无数据",
	// Toast.LENGTH_SHORT).show();
	//
	// } else {
	// for (int i = 0; i < jArray.length(); i++) {
	// json_data = jArray.getJSONObject(i);
	// Log.e("json_data", "" + json_data);
	// JSONObject jsonObject = json_data.getJSONObject("data");
	// CommentModel listinfo = new CommentModel();
	//
	// listinfo.setTime(jsonObject.getString("createTime"));
	// listinfo.setComment(jsonObject.getString("content"));
	// listinfo.setRoundUrl(jsonObject.getString("userPhoto"));
	// listinfo.setName(jsonObject.getString("user_name"));
	// list.add(listinfo);
	//
	// }
	// }
	//
	// } catch (JSONException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// if (arg == 1) {
	// go();
	// } else {
	// mAdapter.notifyDataSetChanged();
	// }
	//
	// }

	// private void GetPager(String pager) {
	// // TODO Auto-generated method stub
	// try {
	// JSONObject demoJson = new JSONObject(pager);
	//
	// totalPage = demoJson.getInt("totalPage");
	//
	// } catch (JSONException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (Exception e) {
	// // TODO: handle exception
	// }
	// }
	//
	// private void GetData() {
	// // TODO Auto-generated method stub
	//
	// // TODO Auto-generated method stub
	// final ArrayList ArrayValues = new ArrayList();
	// ArrayValues.add(new BasicNameValuePair("ticket", ticket));
	// ArrayValues.add(new BasicNameValuePair("chn", chn));
	// ArrayValues.add(new BasicNameValuePair("datakey", Id));
	// ArrayValues.add(new BasicNameValuePair("curPage", "" + curPage));
	// ArrayValues.add(new BasicNameValuePair("pageSize", "" + pageSize));
	// new Thread(new Runnable() { // 开启线程上传文件
	// @Override
	// public void run() {
	// String DueData = "";
	// DueData = HttpGetData.GetData("api/cms/common/getChannelCommentData",
	// ArrayValues);
	// Message msg = new Message();
	// msg.obj = DueData;
	// msg.what = GET_DUE_DATA;
	// uiHandler.sendMessage(msg);
	// }
	// }).start();
	//
	// }

	private void GetDataList(String data, int curPage2) {
		// TODO Auto-generated method stub
		try {
			JSONObject demoJson = new JSONObject(data);

			content = demoJson.getString("content");

			new Thread(new Runnable() { // 开启线程上传文件
				@Override
				public void run() {
					content = getNewContent(content);
					Message msg = new Message();

					msg.what = 0;
					uiHandler.sendMessage(msg);
				}
			}).start();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void starttimedelay() {
		// 原因：不延时的话list会滑到顶部
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {

				Message msg1 = new Message();
				msg1.what = 3;
				uiHandler.sendMessage(msg1);

			}

		}, 2000);
	}

	// private void getdatalist(int arg) {
	// if (arg == 1) {
	// list.clear();
	// }
	// // TODO Auto-generated method stub
	//
	// try {
	//
	// for (int i = 0; i < 10; i++) {
	//
	// CommentModel listinfo = new CommentModel();
	// listinfo.setTime("2016-12-14 20:00:00");
	// listinfo.setComment("这真是一篇好文章，学习了");
	// listinfo.setRoundUrl("");
	// listinfo.setName("刘志刚");
	//
	// list.add(listinfo);
	//
	// }
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// if (arg == 1) {
	// go();
	// } else {
	// mAdapter.notifyDataSetChanged();
	// }
	// setlistheight(list.size());
	// if (arg == totalPage) {
	// TextWarning.setText("没有更多了");
	// } else {
	// TextWarning.setText("点击加载更多");
	// }
	// }

	private void setlistheight(int size) {
		// TODO Auto-generated method stub
		screenwidth = getWindow().getWindowManager().getDefaultDisplay().getWidth();
		DisplayMetrics mMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
		scale = getResources().getDisplayMetrics().density;
		dp = screenwidth / scale + 0.5f;
		scalepx = screenwidth / dp;
		int height = (int) (size * 91 * scalepx);
		RelativeLayout.LayoutParams layoutParams1 = (android.widget.RelativeLayout.LayoutParams) ListData
				.getLayoutParams();
		layoutParams1.height = height;
		ListData.setLayoutParams(layoutParams1);
		height = (int) ((screenwidth - 20 * scalepx) / 2);
		layoutParams1 = (android.widget.RelativeLayout.LayoutParams) ImagePic.getLayoutParams();
		layoutParams1.height = height;
		ImagePic.setLayoutParams(layoutParams1);

	}

	// protected void go() {
	// mAdapter = new CommentAdapter(this, list, ListData);
	// ListData.setAdapter(mAdapter);
	// }

	private void settext() {
		// TODO Auto-generated method stub
		// TextWarning.setText("正在加载数据...");
		TextDetail.setText(detail);
		TextTime.setText(Time);
		TextName.setText(Name);
		TextTitle.setText(Title);
	}

	private void initview() {
		// TODO Auto-generated method stub
		// EditAnswer = (EditText) findViewById(R.id.edit_answer);
		// BtnAnswer = (Button) findViewById(R.id.btn_answer);
		ImageBack = (ImageView) findViewById(R.id.image_back);
		ImagePic = (ImageView) findViewById(R.id.image_pic);
		// ScrollLayout = (ScrollView) findViewById(R.id.scrolllayout);
		ListData = (ListView) findViewById(R.id.list_data);
		// TextWarning = (TextView) findViewById(R.id.text_warning);
		TextDetail = (TextView) findViewById(R.id.text_detail);
		TextTime = (TextView) findViewById(R.id.text_time);
		TextName = (TextView) findViewById(R.id.text_name);
		TextTitle = (TextView) findViewById(R.id.text_title);
		text_detail = (TextView) findViewById(R.id.text_detail);
	}

	private void setonclicklistener() {
		// TODO Auto-generated method stub
		// BtnAnswer.setOnClickListener(this);
		ImageBack.setOnClickListener(this);
		ListData.setOnItemClickListener(this);
		// TextWarning.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.image_back:
			finish();
			break;
		// case R.id.btn_answer:
		// break;
		// case R.id.text_warning:
		// curPage++;
		// if (!(curPage > totalPage)) {
		// getdatalist(curPage);
		// Toast.makeText(getApplicationContext(), "正在加载",
		// Toast.LENGTH_SHORT).show();
		// }
		//
		// break;
		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		// CommentModel data = list.get(position);
		// Intent intent = new Intent();
		// intent.setClass(getApplicationContext(),
		// CommentDetailActivity.class);
		// Bundle bundle = new Bundle();
		// bundle.putString("Name", data.getName());
		// bundle.putString("Time", data.getTime());
		// bundle.putString("Comment", data.getComment());
		// intent.putExtras(bundle);
		// startActivity(intent);
	}

}
