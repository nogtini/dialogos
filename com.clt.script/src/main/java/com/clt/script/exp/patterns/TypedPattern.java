package com.clt.script.exp.patterns;

import java.util.Map;

import com.clt.script.exp.Match;
import com.clt.script.exp.Pattern;
import com.clt.script.exp.Type;
import com.clt.script.exp.Value;

/**
 * @author Daniel Bobbert
 * @version 1.0
 */
public class TypedPattern implements Pattern {

    private Type type;
    private Pattern pattern;

    public TypedPattern(Pattern pattern, Type type) {

        this.pattern = pattern;
        this.type = type;
    }

    public Match match(Value v) {

        try {
            if (!Type.equals(this.type, v.getType())) {
                return null;
            }
        } catch (Exception exn) {
            return null;
        }

        return this.pattern.match(v);
    }

    public Type getType(Map<String, Type> variableTypes) {

        Type.unify(this.type, this.pattern.getType(variableTypes));
        return this.type;
    }

    public Pattern.VarSet getFreeVars() {

        return this.pattern.getFreeVars();
    }

    @Override
    public String toString() {

        return this.pattern + " : " + this.type;
    }
}
