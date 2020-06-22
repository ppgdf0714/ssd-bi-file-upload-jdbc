package jp.co.ssd.bi.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import jp.co.ssd.bi.constant.UploadCommonConst;
import jp.co.ssd.bi.model.MyException;
import jp.co.ssd.bi.service.FileUploadService;
import jp.co.ssd.bi.util.DBUtil;

@Controller
@Transactional
public class FileUploadController {
	@Autowired
	DBUtil dbutil;
	@Autowired
	FileUploadService fileUploadService;
	@Value("${jp.co.sdd.bi.xmlname}")
	private String xmlname;
	/**
	 * 初期ページ表示
	 */
	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		return mv;
	}
	
	/**
     *　ファイルアップロード
     *
     * @param filetype ファイルタイプ
     * @param file アップロードファイル
     * @return 
     * @throws Exception
     */
	@RequestMapping("/importExcel")
    public String importExcel(@RequestParam(value = "filetype",required = false) String filetype,MultipartFile file){
//		try {
//		//XMLファイル読み込み	
//		Map<String,List<String>> xmlData = fileUploadService.xmlLoad(filetype,xmlname);
//		//excelファイル読み込み
//		Map<String,List<String>> excelData = fileUploadService.getExcelData(filetype,file,xmlData);
//		//テーブルを更新
//		fileUploadService.dataUpload(filetype,excelData);
//		}catch(Exception e) {
//			throw new MyException(e.getMessage());
//		}

		
		
		try {
			Connection myconn = null;
			try {
				Class.forName("org.postgresql.Driver").newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				throw new MyException("111");
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				throw new MyException("222");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				throw new MyException("333");
			}
			myconn = DriverManager.getConnection("jdbc:postgresql://ssd-rds-postpresql.caunxszlefde.ap-northeast-1.rds.amazonaws.com:5432/ssdDatabase", "ssdmaster", "ssdpassword");
			
		//Connection myconn = dbutil.getConn();
		myconn.setAutoCommit(false);
		PreparedStatement pStatement = null;
	    pStatement = myconn.prepareStatement("delete from 案件振り返り_テスト");
	    pStatement.executeUpdate();
		myconn.commit();
		throw new MyException("444");}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new MyException("555");
		}
   	//return "OK";
    }

}
