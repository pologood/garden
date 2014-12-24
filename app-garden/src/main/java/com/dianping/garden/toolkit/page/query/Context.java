package com.dianping.garden.toolkit.page.query;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.dianping.garden.dashboard.DashboardContext;
import com.dianping.garden.view.UriBuilder;
import org.unidal.dal.jdbc.raw.RawDataObject;

public class Context extends DashboardContext<Payload> {
   private SimpleDateFormat m_dateformat = new SimpleDateFormat("HH:mm:ss.SSS");

   private List<RawDataObject> m_rawDataObjects;

   private long m_queryElapsed;

   public long getQueryElapsed() {
      return m_queryElapsed;
   }

   public List<RawDataObject> getRawDataObjects() {
      return m_rawDataObjects;
   }

   public void log(String format, Object... args) {
      System.out.print("[" + m_dateformat.format(new Date()) + "] ");
      System.out.println(String.format(format, args));
   }

   public void redirect(Model model, Object path) {
      String uri = UriBuilder.uri(model, path);

      redirect(uri);
   }

   public void redirect(Model model, Object path, String qs) {
      String uri = UriBuilder.uri2(model, path, qs);

      redirect(uri);
   }

   public void setQueryElapsed(long queryElapsed) {
      m_queryElapsed = queryElapsed;
   }

   public void setRawDataObjects(List<RawDataObject> rawDataObjects) {
      m_rawDataObjects = rawDataObjects;
   }
}