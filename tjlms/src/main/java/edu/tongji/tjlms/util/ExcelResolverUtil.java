package edu.tongji.tjlms.util;

import edu.tongji.tjlms.model.StudentEntity;
import edu.tongji.tjlms.model.TakesEntity;
import edu.tongji.tjlms.model.TeacherEntity;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelResolverUtil {
    public static List<StudentEntity> resolveSheetStudent(String filePath)
    {
        List<StudentEntity> list = new ArrayList<>();
        try
        {
            File excel = new File(filePath);
            if (excel.isFile() && excel.exists())
            {

                String[] split = excel.getName().split("\\.");
                Workbook wb;
                if ( "xls".equals(split[1]))
                {
                    FileInputStream fis = new FileInputStream(excel);
                    wb = new HSSFWorkbook(fis);
                }
                else if ("xlsx".equals(split[1]))
                {
                    wb = new XSSFWorkbook(excel);
                }
                else
                {
                    System.out.println("文件类型错误!");
                    return null;
                }

                Sheet sheet = wb.getSheetAt(0);

                int firstRowIndex = sheet.getFirstRowNum()+1;
                int lastRowIndex = sheet.getLastRowNum();
                for(int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++)
                {
                    StudentEntity temp = new StudentEntity();
                    temp.setEmailAddr(null);
                    temp.setPassword(null);
                    temp.setVerified(false);
                    temp.setId(null);
                    temp.setName(null);
                    Row row = sheet.getRow(rIndex);
                    if (row != null) {
                        int firstCellIndex = row.getFirstCellNum();
                        int lastCellIndex = row.getLastCellNum();
                        for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++)
                        {
                            Cell cell = row.getCell(cIndex);
                            if (cell != null)
                            {
                               if(cIndex == 0)
                               {
                                   temp.setId(cell.toString());
                               }
                               else if(cIndex == 1)
                               {
                                   temp.setName(cell.toString());
                               }
                            }
                        }
                    }
                    if(temp.getName()!=null && temp.getId()!=null)
                    {
                        list.add(temp);
                    }
                }
                wb.close();
                return list;
            }
            else
            {
                System.out.println("找不到指定的文件");
                return null;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public static List<TeacherEntity> resolveSheetTeacher(String filePath)
    {
        List<TeacherEntity> list = new ArrayList<>();
        try
        {
            File excel = new File(filePath);
            if (excel.isFile() && excel.exists())
            {

                String[] split = excel.getName().split("\\.");
                Workbook wb;
                if ( "xls".equals(split[1]))
                {
                    FileInputStream fis = new FileInputStream(excel);
                    wb = new HSSFWorkbook(fis);
                }
                else if ("xlsx".equals(split[1]))
                {
                    wb = new XSSFWorkbook(excel);
                }
                else
                {
                    System.err.println("文件类型错误!");
                    return null;
                }

                Sheet sheet = wb.getSheetAt(0);

                int firstRowIndex = sheet.getFirstRowNum()+1;
                int lastRowIndex = sheet.getLastRowNum();
                for(int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++)
                {
                    TeacherEntity temp = new TeacherEntity();
                    temp.setEmailAddr(null);
                    temp.setPassword(null);
                    temp.setVerified(false);
                    temp.setId(null);
                    temp.setName(null);
                    temp.setGrade(false);
                    temp.setReleaseLab(false);
                    temp.setType(1);
                    Row row = sheet.getRow(rIndex);
                    if (row != null) {
                        int firstCellIndex = row.getFirstCellNum();
                        int lastCellIndex = row.getLastCellNum();
                        for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++)
                        {
                            Cell cell = row.getCell(cIndex);
                            if (cell != null)
                            {
                                if(cIndex == 0)
                                {
                                    temp.setId(cell.toString());
                                }
                                else if(cIndex == 1)
                                {
                                    temp.setName(cell.toString());
                                }
                            }
                        }
                    }
                    if(temp.getName()!=null && temp.getId()!=null)
                    {
                        list.add(temp);
                    }
                }
                wb.close();
                return list;
            }
            else
            {
                System.out.println("找不到指定的文件");
                return null;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public static List<TakesEntity> resolveClassStudent(String filePath,String classId)
    {
        List<TakesEntity> list = new ArrayList<>();
        try
        {
            File excel = new File(filePath);
            if (excel.isFile() && excel.exists()) {

                String[] split = excel.getName().split("\\.");
                Workbook wb;
                if ("xls".equals(split[1])) {
                    FileInputStream fis = new FileInputStream(excel);
                    wb = new HSSFWorkbook(fis);
                } else if ("xlsx".equals(split[1])) {
                    wb = new XSSFWorkbook(excel);
                } else {
                    System.out.println("文件类型错误!");
                    return null;
                }

                Sheet sheet = wb.getSheetAt(0);
                int firstRowIndex = sheet.getFirstRowNum()+1;
                int lastRowIndex = sheet.getLastRowNum();
                for(int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++)
                {
                    TakesEntity temp = new TakesEntity();
                    temp.setClassId(classId);
                    Row row = sheet.getRow(rIndex);
                    if (row != null) {
                        int firstCellIndex = row.getFirstCellNum();
                        int lastCellIndex = row.getLastCellNum();
                        for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++)
                        {
                            Cell cell = row.getCell(cIndex);
                            if (cell != null)
                            {
                                if(cIndex == 0)
                                {
                                    temp.setStuId(cell.toString());
                                }
                                else if(cIndex == 1)
                                {
                                    temp.setStuName(cell.toString());
                                }
                            }
                        }
                    }
                    if(temp.getStuName()!=null && temp.getStuId()!=null)
                    {
                        list.add(temp);
                    }
                }
                wb.close();
                return list;

            }
            else
            {
                System.out.println("找不到指定的文件");
                return null;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

}
