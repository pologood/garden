package com.dianping.garden.dashboard;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.unidal.webres.resource.runtime.ResourceConfigurator;
import org.unidal.webres.resource.runtime.ResourceInitializer;
import org.unidal.webres.resource.runtime.ResourceRuntime;
import org.unidal.webres.resource.runtime.ResourceRuntimeContext;
import org.unidal.webres.resource.spi.IResourceRegistry;
import org.unidal.webres.tag.resource.ResourceTagConfigurator;
import org.unidal.webres.taglib.basic.ResourceTagLibConfigurator;
import org.unidal.web.mvc.Action;
import org.unidal.web.mvc.ActionContext;
import org.unidal.web.mvc.ActionPayload;
import org.unidal.web.mvc.Page;

public class DashboardContext<T extends ActionPayload<? extends Page, ? extends Action>> extends ActionContext<T> {

   @SuppressWarnings("deprecation")
   @Override
   public void initialize(HttpServletRequest request, HttpServletResponse response) {
      super.initialize(request, response);

      String contextPath = request.getContextPath();

      if (!ResourceRuntime.INSTANCE.hasConfig(contextPath)) {
         File warRoot = new File(request.getRealPath("/"));

         ResourceRuntime.INSTANCE.removeConfig(contextPath);
         ResourceInitializer.initialize(contextPath, warRoot);

         IResourceRegistry registry = ResourceRuntime.INSTANCE.getConfig(contextPath).getRegistry();

         new ResourceConfigurator().configure(registry);
         new ResourceTagConfigurator().configure(registry);
         new ResourceTagLibConfigurator().configure(registry);
      }

      ResourceRuntimeContext.setup(contextPath);
   }

}
