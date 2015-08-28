/**
 * Copyright (c) 2014 Samsung Electronics, Inc.,
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * 
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package com.samsung.sec.dexter.findbugs.plugin;

import net.xeoh.plugins.base.annotations.PluginImplementation;

import org.osgi.framework.Version;

import com.samsung.sec.dexter.core.analyzer.AnalysisConfig;
import com.samsung.sec.dexter.core.analyzer.AnalysisResult;
import com.samsung.sec.dexter.core.checker.CheckerConfig;
import com.samsung.sec.dexter.core.config.DexterConfig;
import com.samsung.sec.dexter.core.config.DexterConfig.LANGUAGE;
import com.samsung.sec.dexter.core.plugin.IDexterPlugin;
import com.samsung.sec.dexter.core.plugin.PluginDescription;
import com.samsung.sec.dexter.core.plugin.PluginVersion;
import com.samsung.sec.dexter.findbugs.FindBugsActivator;

@PluginImplementation
public class FindbugsDexterPlugin implements IDexterPlugin {
	private FindBugsWrapper findbug = new FindBugsWrapper();
	public final static String PLUGIN_NAME = "findbugs";
	private PluginDescription pluginDescription;
	
	/* (non-Javadoc)
	 * @see com.samsung.sec.dexter.core.plugin.IDexterPlugin#init()
	 */
    @Override
    public void init(){
    	findbug.initCheckerConfig();
    }
    
    @Override
    public void destroy() {
    	// do nothing
    }
    
    @Override
    public PluginDescription getDexterPluginDescription() {
    	if(this.pluginDescription == null){
    		this.pluginDescription = new PluginDescription(PLUGIN_NAME, PLUGIN_NAME, 
    				new PluginVersion(0,9,0), 
    				DexterConfig.LANGUAGE.JAVA, "Dexter plug-in for FindBugs");
    	}
	    return this.pluginDescription;
    }

    @Override
    public AnalysisResult analyze(AnalysisConfig config){
    	return findbug.execute(config);
    }

	/* (non-Javadoc)
	 * @see com.samsung.sec.dexter.core.plugin.IDexterPlugin#getCheckerConfig()
	 */
    @Override
    public CheckerConfig getCheckerConfig(){
	    return findbug.getCheckerConfig();
    }

	/* (non-Javadoc)
	 * @see com.samsung.sec.dexter.core.plugin.IDexterPlugin#setCheckerConfig(com.samsung.sec.dexter.core.checker.CheckerConfig)
	 */
    @Override
    public void setCheckerConfig(CheckerConfig cc) {
    	findbug.setCheckerConfig(cc);
    }

	/* (non-Javadoc)
	 * @see com.samsung.sec.dexter.core.plugin.IDexterPlugin#supportLanguage(com.samsung.sec.dexter.core.util.DexterConfig.LANGUAGE)
	 */
    @Override
    public boolean supportLanguage(LANGUAGE language) {
    	if(language == DexterConfig.LANGUAGE.JAVA){
    		return true;
    	} else {
    		return false;
    	}
    }

	@Override
    public void handleDexterHomeChanged(String oldPath, String newPath) {
		// do nothing
    }

	@Override
    public String[] getSupportingFileExtensions() {
		return new String[] {"java"};
    }
}
