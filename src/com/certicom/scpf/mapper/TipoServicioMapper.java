package com.certicom.scpf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.scpf.domain.TipoServicio;

public interface TipoServicioMapper {

	@Select("select * from t_tipo_servicio order by id_tipo_servicio asc")
	List<TipoServicio> findAll();
	
	@Select("select * from t_tipo_servicio where genera_ticket='TRUE' order by id_tipo_servicio asc")
	List<TipoServicio> findAllForTicket();

	@Update("update t_tipo_servicio set  descripcion_tipo = #{descripcion_tipo}, genera_ticket = #{genera_ticket}  "
			+ "where id_tipo_servicio= #{id_tipo_servicio}")
	@Options(flushCache=true,useCache=true)
	void actualizartipoServicio(TipoServicio tipoServicioSelec);

	@Insert("insert into t_tipo_servicio (descripcion_tipo, genera_ticket) "
   + " values ( #{descripcion_tipo}, #{genera_ticket})")
	void creartipoServicio(TipoServicio tipoServicioSelec);

	@Delete("delete from t_tipo_servicio  where id_tipo_servicio = #{id_tipo_servicio}")
	@Options(flushCache=true)
	void eliminartipoServicio(Integer id_tipo_servicio);

}
