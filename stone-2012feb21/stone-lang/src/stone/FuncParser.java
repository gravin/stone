package stone;
import static stone.Parser.rule;
import stone.ast.ParameterList;
import stone.ast.Arguments;
import stone.ast.DefStmnt;

public class FuncParser extends BasicParser {
    Parser param = rule().identifier(reserved);
    Parser params = rule(ParameterList.class)
                        .ast(param).repeat(rule().sep(",").ast(param));
    Parser paramList = rule().sep("(").maybe(params).sep(")");
    Parser def = rule(DefStmnt.class)
                     .sep("def").identifier(reserved).ast(paramList).ast(block);
    Parser args = rule(Arguments.class)
                      .ast(expr).repeat(rule().sep(",").ast(expr));
    Parser postfix = rule().sep("(").maybe(args).sep(")");

    public FuncParser() {
        reserved.add(")");
        primary.repeat(postfix);
        simple.option(args);
        program.insertChoice(def);
    }
}
