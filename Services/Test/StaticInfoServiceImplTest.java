import com.forum.server.dto.staticInfo.SectionCreateDto;
import com.forum.server.services.implementations.StaticInfoServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


import static org.junit.Assert.*;
/**
 * Created by root on 14.09.16.
 */
public class StaticInfoServiceImplTest {
    private static final String AUTH_TOKEN = "asdfghj";

    @Autowired
    private StaticInfoServiceImpl staticInfoService;

    @Test
    public void createSection() {
        SectionCreateDto sectionCreateDto = new SectionCreateDto.Builder()
                .setName("new")
                .setUrl("new")
                .build();
        staticInfoService.createSection(AUTH_TOKEN, sectionCreateDto);

    }
}
