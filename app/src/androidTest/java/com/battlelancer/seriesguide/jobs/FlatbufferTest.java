package com.battlelancer.seriesguide.jobs;

import static com.google.common.truth.Truth.assertThat;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.google.flatbuffers.FlatBufferBuilder;
import java.nio.ByteBuffer;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class FlatbufferTest {

    @Test
    public void createAndReadBuffer() {
        FlatBufferBuilder builder = new FlatBufferBuilder(0);

        int[] episodeInfos = new int[42];
        for (int i = 0; i < 21; i++) {
            episodeInfos[i] = EpisodeInfo.createEpisodeInfo(builder, 1, i + 1);
        }
        for (int i = 21; i < 42; i++) {
            episodeInfos[i] = EpisodeInfo.createEpisodeInfo(builder, 2, i + 1);
        }

        int episodes = SgJobInfo.createEpisodesVector(builder, episodeInfos);
        int jobInfo = SgJobInfo.createSgJobInfo(builder, 42, 1, episodes, 0);

        builder.finish(jobInfo);

        byte[] bytes = builder.sizedByteArray();

        ByteBuffer bufferReloaded = ByteBuffer.wrap(bytes);

        SgJobInfo jobInfoReloaded = SgJobInfo.getRootAsSgJobInfo(bufferReloaded);

        assertThat(jobInfoReloaded.showTvdbId()).isEqualTo(42);
        assertThat(jobInfoReloaded.flagValue()).isEqualTo(1);
        assertThat(jobInfoReloaded.episodesLength()).isEqualTo(42);
        for (int i = 0; i < 21; i++) {
            EpisodeInfo episodeInfo = jobInfoReloaded.episodes(i);
            assertThat(episodeInfo.season()).isEqualTo(1);
            assertThat(episodeInfo.number()).isEqualTo(i + 1);
        }
        for (int i = 21; i < 42; i++) {
            EpisodeInfo episodeInfo = jobInfoReloaded.episodes(i);
            assertThat(episodeInfo.season()).isEqualTo(2);
            assertThat(episodeInfo.number()).isEqualTo(i + 1);
        }
    }

    @Test
    public void movieId() {
        FlatBufferBuilder builder = new FlatBufferBuilder(0);

        int jobInfo = SgJobInfo.createSgJobInfo(builder, 0, 0, 0, 42);

        builder.finish(jobInfo);

        byte[] bytes = builder.sizedByteArray();

        ByteBuffer bufferReloaded = ByteBuffer.wrap(bytes);

        SgJobInfo jobInfoReloaded = SgJobInfo.getRootAsSgJobInfo(bufferReloaded);

        assertThat(jobInfoReloaded.showTvdbId()).isEqualTo(0);
        assertThat(jobInfoReloaded.flagValue()).isEqualTo(0);
        assertThat(jobInfoReloaded.episodesLength()).isEqualTo(0);
        assertThat(jobInfoReloaded.movieTmdbId()).isEqualTo(42);
    }

}
