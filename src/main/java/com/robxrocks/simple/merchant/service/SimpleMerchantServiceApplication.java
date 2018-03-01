package com.robxrocks.simple.merchant.service;

import com.robxrocks.simple.merchant.service.dao.OfferDao;
import com.robxrocks.simple.merchant.service.dao.OfferDaoImpl;
import com.robxrocks.simple.merchant.service.db.Datastore;
import com.robxrocks.simple.merchant.service.db.MemoryDatastoreImpl;
import com.robxrocks.simple.merchant.service.resources.OfferResource;
import com.robxrocks.simple.merchant.service.services.OfferService;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class SimpleMerchantServiceApplication extends Application<SimpleMerchantServiceConfiguration> {

    private Datastore datastore;
    private OfferDao offerDao;
    private OfferService offerService;

    public static void main(final String[] args) throws Exception {
        new SimpleMerchantServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "SimpleMerchantService";
    }

    @Override
    public void initialize(final Bootstrap<SimpleMerchantServiceConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<SimpleMerchantServiceConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(SimpleMerchantServiceConfiguration configuration) {
                return configuration.swaggerBundleConfiguration;
            }
        });
    }

    @Override
    public void run(final SimpleMerchantServiceConfiguration configuration,
                    final Environment environment) {
        if (null == datastore) {
            datastore = new MemoryDatastoreImpl();
        }
        if (null == offerDao) {
            offerDao = new OfferDaoImpl(datastore);
        }
        if (null == offerService) {
            offerService = new OfferService();
        }

        environment.jersey().register(OfferResource.builder()
                .offerDao(offerDao)
                .offerService(offerService)
                .build()
        );
    }

}
