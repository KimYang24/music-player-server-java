package com.example.webmusic.controller.artist.out;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OutApiGetArtistDescribe {
    private long code;
    private Describe describe;

    @Data
    public static class Describe{

        /**
         * 获奖荣誉
         */
        private String award="正表压按数写运是中建民手后白。使构积作产根素线地素达常三积。政较公关根必度价年张如下命经四。三向满关队接持走军般计样。办联我变光热志权种次几新。";
        /**
         * 基本信息
         */
        private String basicInfo="声反市群光包保山厂很至边论称。第子专持般者千自许实从意知张。历采任这前元研青军专六又理期志应装。那条不近只况委或反情单开常称。研者斗合价本革认当话广利质矿。";
        /**
         * 简短介绍
         */
        private String profile;
        /**
         * 从艺历程
         */
        private String timeLine="越术三没图员习将把形且马因这律。火公展次状华计状手议金住记元示组光类。明参布去建山难六先能酸外条速。厂阶或被感最共式何回也满段门因来形话。内造带及只要样元量动备布。律行清带习北市题千们百指际完边。生众切算小基压商对属阶列。";

    }

}
