package com.ramselabs.education.serviceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Scope;

import com.ramselabs.education.dao.service.UserDAO;
import com.ramselabs.education.entity.UserProfile;
import com.ramselabs.education.model.AutocompleteTemplate;
import com.ramselabs.education.model.UploadModel;
import com.ramselabs.education.service.UserService;

@Named
@Scope("session")
public class UserServiceImpl implements UserService, Serializable {

	private static final long serialVersionUID = 1L;
	private Map<String, String> sheetMaps = new HashMap<String, String>();
	@Inject
	private UserDAO userDao;

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	@Override
	public Map<String, String> getSheetMaps() {
		try {
			InputStream input = UserServiceImpl.class
					.getResourceAsStream("/../classes/" + "Book.xlsx");
			if (input == null)
				return null;
			XSSFWorkbook wb = new XSSFWorkbook(input);
			for (int numOfSheet = 0; numOfSheet < wb.getNumberOfSheets(); numOfSheet++) {
				sheetMaps.put(wb.getSheetName(numOfSheet),
						wb.getSheetName(numOfSheet));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return sheetMaps;
	}

	public void setSheetMaps(Map<String, String> sheetMaps) {
		this.sheetMaps = sheetMaps;
	}

	@Override
	public String doLogin(UserProfile user) {

		return userDao.loginAuthenticate(user);
	}

	@Override
	public List<AutocompleteTemplate> getAutocompleteUserList(UserProfile user) {
		UserProfile userProfile = getPersistentUser(user);
		int userId = userProfile.getUserId();
		System.out.println("User Service" + userId);
		return userDao.getUserAutoCompleteList(userId);
	}

	@Override
	public UserProfile getPersistentUser(UserProfile user) {
		return userDao.getPersistentUser(user);
	}

	@Override
	public int getUserId(UserProfile user) {
		return userDao.getUserId(user);
	}

	@Override
	public UserProfile getUserProfile(UserProfile user) {
		return userDao.getUserProfile(user);
	}

	@Override
	public int updateUserImage(UserProfile user) {
		return userDao.updateUserImage(user);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<UploadModel> getExcelModel(String sheetName) {
		List<Map> listOfMap = new ArrayList<Map>();
		List<UploadModel> modelList = new ArrayList<UploadModel>();
		Class cls = null;
		Object obj = null;
		listOfMap = getExcelObj(sheetName);
		if (listOfMap == null)
			return null;
		try {
			for (Map map : listOfMap) {
				Set<String> keys = map.keySet();
				if(sheetName.equalsIgnoreCase("Users")){
					   cls = Class.forName("com.ramselabs.education.model.UserModel");
				       obj = cls.newInstance();
				}
				else if(sheetName.equalsIgnoreCase("Groups")){
					cls=Class.forName("com.ramselabs.education.model.GroupUploadModel");
					obj = cls.newInstance();
				}
				else if(sheetName.equalsIgnoreCase("GroupUsers")){
					cls=Class.forName("com.ramselabs.education.model.GroupUserUploadModel");
					obj = cls.newInstance();
				}
				for (String field : keys) {

					String methodName = "set"
							+ field.substring(0, 1).toUpperCase()
							+ field.substring(1);
					for (Method m : cls.getDeclaredMethods()) {
						if (m.getName().equals(methodName)) {
							Class<?>[] pType = m.getParameterTypes();

							for (int i = 0; i < pType.length; i++) {
								String s1 = pType[i].toString();
								String s2 = "class" + " " + "java.lang.String";
								String s3 = "class" + " " + "java.lang.Integer";
								if (s1.equals(s2)) {
									m.invoke(obj, map.get(field));
								}
								if (s1.equals(s3)) {
									m.invoke(
											obj,
											new Integer((String) map.get(field)));
								}
							}
						}
					}

				}
				modelList.add((UploadModel) obj);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return modelList;
	}

	@SuppressWarnings("rawtypes")
	private List<Map> getExcelObj(String sheetName) {
		List<String> fields = new ArrayList<String>();
		List<Map> list = new ArrayList<Map>();
		try {

			InputStream input = UserServiceImpl.class
					.getResourceAsStream("/../classes/" + "Book.xlsx");
			if (input == null)
				return null;
			XSSFWorkbook wb = new XSSFWorkbook(input);
			for (int numOfSheet = 0; numOfSheet < wb.getNumberOfSheets(); numOfSheet++) {
				if (wb.getSheetName(numOfSheet).equalsIgnoreCase(sheetName)) {
					XSSFSheet sheet = wb.getSheetAt(numOfSheet);
					if (sheetName.equalsIgnoreCase("Users")) {
						for (Row row : sheet) {
							Map<String, String> map = new HashMap<String, String>();
							if (row.getRowNum() == 0) {
								for (Cell cell : row) {
									int i = cell.getColumnIndex();
									switch (i) {
									// column 0 = userName
									case 0:
										fields.add(cell.getStringCellValue());
										break;
									// column 1 = password
									case 1:
										fields.add(cell.getStringCellValue());
										break;
									// column 3 = displayName
									case 2:
										fields.add(cell.getStringCellValue());
										break;
									}
								}
							} else {
								for (Cell cell : row) {
									int i = cell.getColumnIndex();
									switch (i) {
									// column 0 = userName
									case 0:
										map.put(fields.get(i),
												cell.getStringCellValue());
										break;
									// column 1 = password
									case 1:
										map.put(fields.get(i),
												Integer.toString((int) (cell
														.getNumericCellValue())));
										break;
									// column 3 = displayName
									case 2:
										map.put(fields.get(i),
												cell.getStringCellValue());
										break;
									}//switch
								}//for
								list.add(map);
							}//else

						}//for column
						System.out.println(list);
					}//main if compare sheetType
					if (sheetName.equalsIgnoreCase("GroupUsers")) {
						for (Row row : sheet) {
							Map<String, String> map = new HashMap<String, String>();
							if (row.getRowNum() == 0) {
								for (Cell cell : row) {
									int i = cell.getColumnIndex();
									switch (i) {
									// column 0 = groupName
									case 0:
										fields.add(cell.getStringCellValue());
										break;
									// column 1 = userName
									case 1:
										fields.add(cell.getStringCellValue());
										break;
									}
								}
							} else {
								for (Cell cell : row) {
									int i = cell.getColumnIndex();
									switch (i) {
									// column 0 = groupName
									case 0:
										map.put(fields.get(i),
												cell.getStringCellValue());
										break;
									// column 1 = userName
									case 1:
										map.put(fields.get(i),
												cell.getStringCellValue());
										break;
									}//switch
								}//for
								list.add(map);
							}//else

						}//for column
						System.out.println(list);
					}//main if compare sheetType
					if(sheetName.equalsIgnoreCase("Groups")){
						for (Row row : sheet) {
							Map<String, String> map = new HashMap<String, String>();
							if (row.getRowNum() == 0) {
								for (Cell cell : row) {
									int i = cell.getColumnIndex();
									switch (i) {
									// column 0 = displayName
									case 0:
										fields.add(cell.getStringCellValue());
										break;
									// column 1 = grade
									case 1:
										fields.add(cell.getStringCellValue());
										break;
									// column 2 = area
									case 2:
										fields.add(cell.getStringCellValue());
										break;
									// column 3 = moderateOptions	
									case 3:
										fields.add(cell.getStringCellValue());
										break;
									// column 4 = groupDescription	
									case 4:
										fields.add(cell.getStringCellValue());
										break;
									}
								}
							} else {
								for (Cell cell : row) {
									int i = cell.getColumnIndex();
									switch (i) {
									// column 0 = displayName
									case 0:
										map.put(fields.get(i),
												cell.getStringCellValue());
										break;
									// column 1 = grade
									case 1:
										map.put(fields.get(i),
												cell.getStringCellValue());
										break;
									// column 2 = area
									case 2:
										map.put(fields.get(i),
												cell.getStringCellValue());
										break;
									// column 3 = moderateOptions	
									case 3:
										map.put(fields.get(i),
												cell.getStringCellValue());
										break;
									// column 4 = groupDescription	
									case 4:
										map.put(fields.get(i),
												cell.getStringCellValue());
										break;
									}
								}//for
								list.add(map);
							}//else

						}//for column
						System.out.println(list);
					}
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return list;
	}

	@Override
	public int insertUser(UserProfile user) {
		return userDao.insertUser(user);
	}
}
