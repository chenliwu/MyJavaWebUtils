package com.chenlw.java.web.utils.utils;

import java.nio.charset.StandardCharsets;

/**
 * @author chenlw
 * @date 2019/10/17
 */
public class HtmlUtils {

    public static final String TEST_STRING="{&quot;sql&quot;:&quot;select \\nt.type_name as \\&quot;银行类别\\&quot;,\\nbankbal.currency_name as \\&quot;币别\\&quot;,\\nsum(balMoney) as \\&quot;余额总金额\\&quot;\\nfrom (\\n\\tselect \\n\\tdistinct (b.bank_acc),\\n\\tb.bank_type_id, \\n\\t(\\n\\t\\tselect \\n\\t\\t\\tdistinct a.avail_bal\\n\\t\\tfrom bis_acc_bal a,\\n\\t\\t(\\n\\t\\t\\tselect \\n\\t\\t\\t\\tbank_acc, \\n\\t\\t\\t\\tmax(bal_date) maxdate\\n\\t\\t\\tfrom bis_acc_bal ab \\n\\t\\t\\twhere 1 = 1\\n\\t\\t\\tand (ab.status &gt;= &#39;95&#39; or ab.status is null)\\n\\t\\t\\tgroup by bank_acc\\n\\t\\t) abc\\n\\t\\twhere a.bank_acc = abc.bank_acc\\n\\t\\tand a.bal_date = abc.maxdate\\n\\t\\tand a.cur_id = c.cur_id\\n\\t\\tand a.bank_acc = b.bank_acc\\n\\t\\tand (a.status is null or a.status &gt;= 95)\\n\\t) as balMoney,\\n\\tbt_currency.cur_name as currency_name\\n\\tfrom bt_bank_acc b\\n\\tinner join bt_bank_acc_cur c on c.bank_acc_id = b.id  \\n\\tinner join bt_currency on c.cur_id = bt_currency.id\\n\\twhere 1 = 1\\n\\tand b.status &lt;&gt; &#39;-2&#39;\\n\\tand b.valid_sign = &#39;1&#39;\\n) bankbal\\nleft join bt_bank_type t on t.id = bankbal.bank_type_id\\nwhere bankbal.balMoney is not null\\ngroup by bankbal.bank_type_id,bankbal.currency_name,t.type_name&quot;}";

    public static void main(String[] args) {
        // 将转义字符转回字符串
        String data = org.springframework.web.util.HtmlUtils.htmlUnescape(TEST_STRING);
        System.out.println(data);
    }

}
