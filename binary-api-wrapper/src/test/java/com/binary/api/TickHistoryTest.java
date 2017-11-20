package com.binary.api;

import com.binary.api.models.enums.TickStyles;
import com.binary.api.models.requests.TickHistoryRequest;
import com.binary.api.models.responses.AssetIndex;
import com.binary.api.models.responses.ResponseBase;
import com.binary.api.models.responses.TickHistoryResponse;
import com.binary.api.models.responses.TickResponse;
import com.binary.api.utils.AssetIndexDeserializer;
import com.binary.api.utils.ClassUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.reactivex.observers.TestObserver;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Morteza Tavanarad
 * @version 1.0.0
 * @since 8/3/2017
 */
public class TickHistoryTest {
    private ApiWrapper api;
    @Before
    public void setup() throws Exception{
        this.api = ApiWrapper.build("10");
    }

    @Test
    public void getTickHistory() throws Exception {
        TickHistoryRequest request = new TickHistoryRequest("R_50", "latest");
        request.setSubscribe(1);
        TestObserver<ResponseBase> testObserver = new TestObserver<>();
        request.setCount(10);

        this.api.sendRequest(request)
                .subscribe(testObserver);

        testObserver.await(10, TimeUnit.SECONDS);

        TickHistoryResponse response = (TickHistoryResponse) testObserver.values().get(0);
        TickResponse tickResponse = (TickResponse) testObserver.values().get(1);

        assertEquals(response.getType(), "history");
        assertEquals(response.getError(), null);
        assertTrue(response.getHistory().getPrices().size() == response.getHistory().getTimes().size());

        assertEquals(tickResponse.getType(), "tick");
    }

    @Test
    public void getCandles() throws Exception {
        TickHistoryRequest request = new TickHistoryRequest("R_50", "latest");
        TestObserver<ResponseBase> testObserver = new TestObserver<>();
        request.setStyle(TickStyles.CANDLES);
        request.setSubscribe(1);

        this.api.sendRequest(request)
                .subscribe(testObserver);

        testObserver.await(10, TimeUnit.SECONDS);

        TickHistoryResponse response = (TickHistoryResponse) testObserver.values().get(0);

        assertEquals(response.getType(), "candles");
        assertEquals(response.getError(), null);
        assertTrue(response.getCandles().size() > 0);
    }

    @Test
    public void testResponseWithError() throws Exception {
        String response = "{\"echo_req\":{\"count\":500,\"end\":\"latest\",\"passthrough\":{\"uuid\":\"3c49a755-8edf-4755-a5f6-d3ebc45c8156\"},\"subscribe\":1,\"ticks_history\":\"R_50\"},\"error\":{\"code\":\"AlreadySubscribed\",\"message\":\"You are already subscribed to R_50\"},\"msg_type\":\"ticks_history\",\"passthrough\":{\"uuid\":\"3c49a755-8edf-4755-a5f6-d3ebc45c8156\"}}";

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(AssetIndex.class, new AssetIndexDeserializer());
        Gson gson = gsonBuilder.create();

        TickHistoryResponse responseObject = gson.fromJson(response, ClassUtils.getClassType("ticks_history"));

        assertNotEquals(responseObject.getError(), null);
    }

    // TODO Test subscribe for TickHistory in both styles (Candles, Tick)
}
