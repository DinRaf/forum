package com.forum.server.services.utils;

/**
 * 18.09.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
public class Message {
    public static final String CONFIRM_MAIL(String confirmHash, String nickname) {
          return    "<meta charset=\"utf-8\">\n"+
                    "<style type=\"text/css\">\n"+
                    "\t/* CLIENT-SPECIFIC STYLES */\n"+
                    "\t#outlook a{padding:0;} /* Force Outlook to provide a \"view in browser\" message */\n"+
                    "\t.ReadMsgBody{width:100%;} .ExternalClass{width:100%;} /* Force Hotmail to display emails at full width */\n"+
                    "\t.ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font, .ExternalClass td, .ExternalClass div {line-height: 100%;} /* Force Hotmail to display normal line spacing */\n"+
                    "\tbody, table, td, a{-webkit-text-size-adjust:100%; -ms-text-size-adjust:100%;} /* Prevent WebKit and Windows mobile changing default text sizes */\n"+
                    "\ttable, td{mso-table-lspace:0pt; mso-table-rspace:0pt;} /* Remove spacing between tables in Outlook 2007 and up */\n"+
                    "\timg{-ms-interpolation-mode:bicubic;} /* Allow smoother rendering of resized image in Internet Explorer */\n"+
                    "\n"+
                    "\t/* RESET STYLES */\n"+
                    "\tbody{margin:0; padding:0;}\n"+
                    "\timg{border:0; height:auto; line-height:100%; outline:none; text-decoration:none;}\n"+
                    "\ttable{border-collapse:collapse !important;}\n"+
                    "\tbody{height:100% !important; margin:0; padding:0; width:100% !important;}\n"+
                    "</style>\n"+
                    "</head>\n"+
                    "<body style=\"margin: 0; padding: 0;\">\n"+
                    "\n"+
                    "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n"+
                    "\t<tr>\n"+
                    "\t\t<td>\n"+
                    "\t\t\t<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n"+
                    "\t\t\t\t<tr>\n"+
                    "\t\t\t\t\t<td align=\"center\" style=\"font-size: 25px; font-family: Helvetica, Arial, sans-serif; color: #333333; padding-top: 30px;\" class=\"padding-copy\">Здравствуйте, "+nickname +"</td>\n"+
                    "\t\t\t\t</tr>\n"+
                    "\t\t\t\t<tr>\n"+
                    "\t\t\t\t\t<td align=\"center\" style=\"padding: 20px 0 0 0; font-size: 16px; line-height: 25px; font-family: Helvetica, Arial, sans-serif; color: #666666;\" class=\"padding-copy\">Вы зарегистрировались на сайте <b>www.labooda.ru</b>. Перейдите по ссылке ниже, чтобы подтвердить email и завершить регистрацию</td>\n"+
                    "\t\t\t\t</tr>\n"+
                    "\t\t\t</table>\n"+
                    "\t\t</td>\n"+
                    "\t</tr>\n"+
                    "\t<tr>\n"+
                    "\t\t<td width=\"100%\" height=\"100px\" valign=\"middle\" align=\"center\">\n"+
                    "\t\t\t<a href=\"http://www.labooda.ru/#confirm/" + confirmHash + "\" target=\"_blank\" style=\"font-size: 16px; font-family: Helvetica, Arial, sans-serif; font-weight: normal; color: #ffffff; text-decoration: none; background-color: #424b5f; border-top: 15px solid #424b5f; border-bottom: 15px solid #424b5f; border-left: 25px solid #424b5f; border-right: 25px solid #424b5f; border-radius: 3px; -webkit-border-radius: 3px; -moz-border-radius: 3px; display: inline-block;\" class=\"mobile-button\">Подтвердить</a>\n"+
                    "\t\t</td>\n"+
                    "\t</tr>\n"+
                    "</table>\n"+
                    "\n"+
                    "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" class=\"responsive-table\">\n"+
                    "\t<tr style=\"height: 50px;\">\n"+
                    "\t\t<td align=\"center\" valign=\"top\" style=\"font-size: 14px; line-height: 18px; font-family: Helvetica, Arial, sans-serif; color:#666666;\">\n"+
                    "\t\t\tЕсли вы не регистрировались, игнорируйте данное письмо\n"+
                    "\t\t</td>\n"+
                    "\t</tr>\n"+
                    "</table>\n"+
                    "\n"+
                    "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" class=\"responsive-table\">\n"+
                    "\t<tr style=\"height: 50px;\">\n"+
                    "\t\t<td align=\"center\" valign=\"middle\" style=\"font-size: 12px; line-height: 18px; font-family: Helvetica, Arial, sans-serif; color:#666666;\">\n"+
                    "\t\t\tС уважением, команда проекта Лабуда\n"+
                    "\t\t</td>\n"+
                    "\t</tr>\n"+
                    "</table>";
    }
    public static final String RECOVERY_PASS(String confirmHash, String nickname) {
        return "<meta charset=\"utf-8\">\n" +
                "<style type=\"text/css\">\n" +
                "\t/* CLIENT-SPECIFIC STYLES */\n" +
                "\t#outlook a{padding:0;} /* Force Outlook to provide a \"view in browser\" message */\n" +
                "\t.ReadMsgBody{width:100%;} .ExternalClass{width:100%;} /* Force Hotmail to display emails at full width */\n" +
                "\t.ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font, .ExternalClass td, .ExternalClass div {line-height: 100%;} /* Force Hotmail to display normal line spacing */\n" +
                "\tbody, table, td, a{-webkit-text-size-adjust:100%; -ms-text-size-adjust:100%;} /* Prevent WebKit and Windows mobile changing default text sizes */\n" +
                "\ttable, td{mso-table-lspace:0pt; mso-table-rspace:0pt;} /* Remove spacing between tables in Outlook 2007 and up */\n" +
                "\timg{-ms-interpolation-mode:bicubic;} /* Allow smoother rendering of resized image in Internet Explorer */\n" +
                "\n" +
                "\t/* RESET STYLES */\n" +
                "\tbody{margin:0; padding:0;}\n" +
                "\timg{border:0; height:auto; line-height:100%; outline:none; text-decoration:none;}\n" +
                "\ttable{border-collapse:collapse !important;}\n" +
                "\tbody{height:100% !important; margin:0; padding:0; width:100% !important;}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body style=\"margin: 0; padding: 0;\">\n" +
                "\n" +
                "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "\t<tr>\n" +
                "\t\t<td>\n" +
                "\t\t\t<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td align=\"center\" style=\"font-size: 25px; font-family: Helvetica, Arial, sans-serif; color: #333333; padding-top: 30px;\" class=\"padding-copy\">Здравствуйте, " + nickname + "</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td align=\"center\" style=\"padding: 20px 0 0 0; font-size: 16px; line-height: 25px; font-family: Helvetica, Arial, sans-serif; color: #666666;\" class=\"padding-copy\">Вы отправили запрос на сброс пароля на сайте <b>www.labooda.ru</b></td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td align=\"center\" style=\"padding: 20px 0 0 0; font-size: 16px; line-height: 25px; font-family: Helvetica, Arial, sans-serif; color: #666666;\" class=\"padding-copy\">Перейдите по ссылке ниже, чтобы установить новый пароль</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t</table>\n" +
                "\t\t</td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td width=\"100%\" height=\"100px\" valign=\"middle\" align=\"center\">\n" +
                "\t\t\t<a href=\"http://www.labooda.ru/#password/" + confirmHash + "\" target=\"_blank\" style=\"font-size: 16px; font-family: Helvetica, Arial, sans-serif; font-weight: normal; color: #ffffff; text-decoration: none; background-color: #424b5f; border-top: 15px solid #424b5f; border-bottom: 15px solid #424b5f; border-left: 25px solid #424b5f; border-right: 25px solid #424b5f; border-radius: 3px; -webkit-border-radius: 3px; -moz-border-radius: 3px; display: inline-block;\" class=\"mobile-button\">Сбросить пароль</a>\n" +
                "\t\t</td>\n" +
                "\t</tr>\n" +
                "</table>\n" +
                "\n" +
                "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" class=\"responsive-table\">\n" +
                "\t<tr style=\"height: 50px;\">\n" +
                "\t\t<td align=\"center\" valign=\"top\" style=\"font-size: 14px; line-height: 18px; font-family: Helvetica, Arial, sans-serif; color:#666666;\">\n" +
                "\t\t\tЕсли вы не собирались сбрасывать пароль, игнорируйте это письмо\n" +
                "\t\t</td>\n" +
                "\t</tr>\n" +
                "</table>\n" +
                "\n" +
                "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" class=\"responsive-table\">\n" +
                "\t<tr style=\"height: 50px;\">\n" +
                "\t\t<td align=\"center\" valign=\"middle\" style=\"font-size: 12px; line-height: 18px; font-family: Helvetica, Arial, sans-serif; color:#666666;\">\n" +
                "\t\t\tС уважением, команда проекта Лабуда\n" +
                "\t\t</td>\n" +
                "\t</tr>\n" +
                "</table>";
    }


    public static void main(String[] args) {
        ConfirmHashGenerator generator = new ConfirmHashGenerator();
        int size = 1;
        String[] hashes = new String[size];
        for (int i = 0; i < size; i++) {
            hashes[i] =  generator.generateHash();
        }
        for (String s: hashes) {
            System.out.println("INSERT INTO tickets (ticket) VALUES ('" + s + "');");
        }
        System.out.println("\n\n\n");

        for (String s: hashes) {
            System.out.println("http://www.labooda.ru/#sign-up/" + s);
        }
    }
}
