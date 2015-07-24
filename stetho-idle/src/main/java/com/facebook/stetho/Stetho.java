package com.facebook.stetho;

import com.facebook.stetho.dumpapp.DumperPlugin;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;
import com.facebook.stetho.server.RegistryInitializer;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;
import org.apache.http.protocol.HttpRequestHandlerRegistry;

import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Initialization and configuration entry point for the Stetho debugging system.  Example usage:
 * <p>
 * <pre>
 *   Stetho.initialize(Stetho.newInitializerBuilder(context)
 *       .enableDumpapp(Stetho.defaultDumperPluginsProvider(context))
 *       .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(context))
 *       .build());
 * </pre>
 */
public class Stetho {
  private static final String LISTENER_THREAD_NAME = "Stetho-Listener";

  private Stetho() {
  }

  public static InitializerBuilder newInitializerBuilder(Context context) {
    return new InitializerBuilder(context);
  }

  /**
   * Start the listening server.  Most of the heavy lifting initialization is deferred until the
   * first socket connection is received, allowing this to be safely used for debug builds on
   * even low-end hardware without noticeably affecting performance.
   */
  public static void initialize(final Initializer initializer) {
    // do nothing
  }

  public static DumperPluginsProvider defaultDumperPluginsProvider(final Context context) {
    return new DumperPluginsProvider() {
      @Override
      public Iterable<DumperPlugin> get() {
        ArrayList<DumperPlugin> plugins = new ArrayList<DumperPlugin>();
        return plugins;
      }
    };
  }

  public static InspectorModulesProvider defaultInspectorModulesProvider(final Context context) {
    return new InspectorModulesProvider() {
      @Override
      public Iterable<ChromeDevtoolsDomain> get() {
        ArrayList<ChromeDevtoolsDomain> modules = new ArrayList<ChromeDevtoolsDomain>();
        return modules;
      }
    };
  }

  /**
   * Callers can choose to subclass this directly to provide the initialization configuration
   * or they can construct a concrete instance using {@link #newInitializerBuilder(Context)}.
   */
  public static abstract class Initializer implements RegistryInitializer {
    private final Context mContext;

    protected Initializer(Context context) {
      mContext = context.getApplicationContext();
    }

    @Override
    public final HttpRequestHandlerRegistry getRegistry() {
      return null;
    }

    protected abstract Iterable<DumperPlugin> getDumperPlugins();

    protected abstract Iterable<ChromeDevtoolsDomain> getInspectorModules();

    protected void addCustomEntries(HttpRequestHandlerRegistry registry) {
      // Override to add stuff...
    }

    private static class LoggingCatchAllHandler implements HttpRequestHandler {
      @Override
      public void handle(
          HttpRequest request,
          HttpResponse response,
          HttpContext context)
          throws HttpException, IOException {

      }
    }
  }

  /**
   * Configure what services are to be enabled in this instance of Stetho.
   */
  public static class InitializerBuilder {
    final Context mContext;

    DumperPluginsProvider mDumperPlugins;
    InspectorModulesProvider mInspectorModules;

    private InitializerBuilder(Context context) {
      mContext = context.getApplicationContext();
    }

    
    public InitializerBuilder enableDumpapp(DumperPluginsProvider plugins) {
      mDumperPlugins = plugins;
      return this;
    }

    public InitializerBuilder enableWebKitInspector(InspectorModulesProvider modules) {
      mInspectorModules = modules;
      return this;
    }

    public Initializer build() {
      return new BuilderBasedInitializer(this);
    }
  }

  private static class BuilderBasedInitializer extends Initializer {
    private final DumperPluginsProvider mDumperPlugins;
    private final InspectorModulesProvider mInspectorModules;

    private BuilderBasedInitializer(InitializerBuilder b) {
      super(b.mContext);
      mDumperPlugins = b.mDumperPlugins;
      mInspectorModules = b.mInspectorModules;
    }

    @Override
    protected Iterable<DumperPlugin> getDumperPlugins() {
      return mDumperPlugins != null ? mDumperPlugins.get() : null;
    }

    @Override
    protected Iterable<ChromeDevtoolsDomain> getInspectorModules() {
      return mInspectorModules != null ? mInspectorModules.get() : null;
    }
  }
}
