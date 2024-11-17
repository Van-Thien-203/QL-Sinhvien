package com.edusys.dao;

import com.edusys.utils.XJdbc;
import com.edusys.entity.NhanVien1;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO1 extends EduSysDAO<NhanVien1, String>{
    public void insert(NhanVien1 model){
        String sql="INSERT INTO NhanVien (MaNV, MatKhau, HoTen, VaiTro) VALUES (?, ?, ?, ?,?)";
        XJdbc.update(sql, 
                model.getMaNV(), 
                model.getMatKhau(), 
                model.getHoTen(),
                model.getSoDT(),
                model.getVaiTro());
                
    }
    
    public void update(NhanVien1 model){
        String sql="UPDATE NhanVien SET MatKhau=?, HoTen=?,SoDT=?, VaiTro=? WHERE MaNV=?";
        XJdbc.update(sql, 
                model.getMatKhau(), 
                model.getHoTen(),
                model.getSoDT(),
                model.getVaiTro(),
                model.getMaNV());
                
        
    }
    
    public void delete(String MaNV){
        String sql="DELETE FROM NhanVien WHERE MaNV=?";
        XJdbc.update(sql, MaNV);
    }
    
    public List<NhanVien1> selectAll(){
        String sql="SELECT * FROM NhanVien";
        return this.selectBySql(sql);
    }
    
    public NhanVien1 selectById(String manv){
        String sql="SELECT * FROM NhanVien WHERE MaNV=?";
        List<NhanVien1> list = this.selectBySql(sql, manv);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    protected List<NhanVien1> selectBySql(String sql, Object...args){
        List<NhanVien1> list=new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while(rs.next()){
                    NhanVien1 entity=new NhanVien1();
                    entity.setMaNV(rs.getString("MaNV"));
                    entity.setMatKhau(rs.getString("MatKhau"));
                    entity.setHoTen(rs.getString("HoTen"));
                    entity.setSoDT(rs.getInt("SoDT"));
                    entity.setVaiTro(rs.getBoolean("VaiTro"));
                    list.add(entity);
                }
            } 
            finally{
                rs.getStatement().getConnection().close();
            }
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }
}
