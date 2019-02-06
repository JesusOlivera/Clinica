package com.certicom.scpf.mapper;


import org.apache.ibatis.annotations.Insert;
import com.certicom.scpf.domain.ComunicacionBaja;


public interface ComunicacionBajaMapper {

	@Insert("insert into t_comunicacion_baja (id_comprobante, tipo_comprobante, "
									+ "id_cliente, id_emisor, "
									+ "id_domicilio_fiscal_cab, id_modo_pago, "
									+ "fecha_comunicacion_cba, descripcion_motivo_cba, "
									+ "estado_sunat) "
									+ "values (#{id_comprobante}, #{tipo_comprobante}, "
									+ "#{id_cliente}, #{id_emisor},"
									+ " #{id_domicilio_fiscal_cab}, #{id_modo_pago},"
									+ " #{fecha_comunicacion_cba}, "
									+ "#{descripcion_motivo_cba}, #{estado_sunat})")
	public void crearComunicacionBaja(ComunicacionBaja comunicacionBaja) throws Exception;
	
}
